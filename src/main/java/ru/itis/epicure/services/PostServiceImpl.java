package ru.itis.epicure.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.dto.PostForm;
import ru.itis.epicure.exceptions.PostNotFoundException;
import ru.itis.epicure.models.*;
import ru.itis.epicure.repository.*;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PostServiceImpl implements PostService {

    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private FilesInfoRepository fileInfoRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PostDto> postsByUserId(Long userId, Long currentUserId) {
        return PostDto.from(postsRepository.findAllByUser_UserId(userId), currentUserId);
    }

    @Override
    public PostDto getPostById(Long postId, Long currentUserId) {
        Post post = postsRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + postId));
        return PostDto.from(post, currentUserId);
    }

    @Override
    @Transactional
    public void addPost(PostForm postForm, Long userId, List<MultipartFile> files) {
        System.out.println("=== ADDING POST ===");
        System.out.println("Title: " + postForm.getTitle());
        System.out.println("Content: " + postForm.getContent());
        System.out.println("RestaurantId: " + postForm.getRestaurantId());
        System.out.println("Files count: " + (files != null ? files.size() : 0));
        
        if (files != null && files.size() > 5) {
            throw new IllegalArgumentException("Можно прикрепить не более 5 файлов");
        }

        User author = usersRepository.findUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository
                .findByRestaurantId(postForm.getRestaurantId());
        
        Post post = Post.builder()
                .title(postForm.getTitle())
                .content(postForm.getContent())
                .rating(postForm.getRating())
                .postDate(new Date())
                .user(author)
                .restaurant(restaurant)
                .build();
        
        // Сначала сохраняем пост
        post = postsRepository.save(post);
        System.out.println("Post saved with ID: " + post.getPostId());
        
        // Затем обрабатываем файлы
        if (files != null && !files.isEmpty()) {
            List<FileInfo> fileInfos = new ArrayList<>();
            
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    System.out.println("Processing file: " + file.getOriginalFilename());
                    try {
                        FileInfo fileInfo = fileStorageService.saveFile(file);
                        System.out.println("File saved successfully: " + fileInfo.getStorageFileName());
                        fileInfos.add(fileInfo);
                    } catch (Exception e) {
                        System.out.println("Error saving file: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            
            // Устанавливаем файлы для поста
            if (!fileInfos.isEmpty()) {
                post.setFiles(fileInfos);
                postsRepository.save(post);
                System.out.println("Пост обновлен с файлами, количество файлов: " + fileInfos.size());
                
                // Принудительно обновляем сессию
                postsRepository.flush();
                
                // Принудительно создаем связи в промежуточной таблице
                for (FileInfo fileInfo : fileInfos) {
                    String sql = "INSERT INTO post_file (post_id, file_id) VALUES (?, ?)";
                    int result = entityManager.createNativeQuery(sql)
                            .setParameter(1, post.getPostId())
                            .setParameter(2, fileInfo.getId())
                            .executeUpdate();
                    System.out.println("Создана связь post_id=" + post.getPostId() + " file_id=" + fileInfo.getId() + " результат=" + result);
                }
                
                // Проверяем, что связи действительно созданы
                Post savedPost = postsRepository.findById(post.getPostId()).orElse(null);
                if (savedPost != null) {
                    System.out.println("Проверка сохраненного поста - количество файлов: " + savedPost.getFiles().size());
                } else {
                    System.out.println("ОШИБКА: Не удалось найти сохраненный пост!");
                }
            }
        }
        
        System.out.println("=== POST ADDED SUCCESSFULLY ===");
    }

    @Override
    @Transactional
    public boolean toggleLikePost(Long postId, Long userId) {
        Post post = postsRepository.getPostByPostId(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + postId));
        User user = usersRepository.findUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Like> existingLike = likesRepository.findByUserIdAndPostId(userId, postId)
                .stream()
                .findFirst();

        if(existingLike.isPresent()) {
            likesRepository.delete(existingLike.get());
            return false; // лайк был удален
        } else {
            Like like = Like.builder()
                    .user(user)
                    .post(post)
                    .build();
            likesRepository.save(like);
            return true; // лайк был добавлен
        }
    }

    @Override
    public int getPostLikeCount(Long postId) {
        return likesRepository.countByPostId(postId); // Лучше использовать countBy
    }

    


}
