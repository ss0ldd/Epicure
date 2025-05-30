package ru.itis.epicure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Long postId;
    private String title;
    private Date date;
    //файл айди, ресторан, юзер айди
}
