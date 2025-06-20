package ru.itis.epicure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.epicure.models.FileInfo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfoDto {
    private Long id;
    private String originalFileName;
    private String storageFileName;
    private String type;
    private Long size;
    private String url;

    public static FileInfoDto from(FileInfo fileInfo) {
        return FileInfoDto.builder()
                .id(fileInfo.getId())
                .originalFileName(fileInfo.getOriginalFileName())
                .storageFileName(fileInfo.getStorageFileName())
                .type(fileInfo.getType())
                .size(fileInfo.getSize())
                .url(fileInfo.getUrl())
                .build();
    }
}
