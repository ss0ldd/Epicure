package ru.itis.epicure.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;
import ru.itis.epicure.dto.ExternalPostDto;

import java.io.IOException;
import java.util.List;

@Service
public class ExternalApiServiceImpl implements ExternalApiService {
    
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public List<ExternalPostDto> getAllPosts() {
        Request request = new Request.Builder()
                .url(BASE_URL + "/posts")
                .build();
                
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to fetch posts: " + response.code());
            }
            
            String responseBody = response.body().string();
            return objectMapper.readValue(responseBody, new TypeReference<List<ExternalPostDto>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error fetching posts", e);
        }
    }
    
    @Override
    public ExternalPostDto getPostById(Long id) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/posts/" + id)
                .build();
                
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to fetch post: " + response.code());
            }
            
            String responseBody = response.body().string();
            return objectMapper.readValue(responseBody, ExternalPostDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Error fetching post", e);
        }
    }
    
    @Override
    public ExternalPostDto createPost(ExternalPostDto post) {
        try {
            String jsonBody = objectMapper.writeValueAsString(post);
            
            RequestBody body = RequestBody.create(
                jsonBody, 
                MediaType.get("application/json; charset=utf-8")
            );
            
            Request request = new Request.Builder()
                    .url(BASE_URL + "/posts")
                    .post(body)
                    .build();
                    
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Failed to create post: " + response.code());
                }
                
                String responseBody = response.body().string();
                return objectMapper.readValue(responseBody, ExternalPostDto.class);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating post", e);
        }
    }
} 