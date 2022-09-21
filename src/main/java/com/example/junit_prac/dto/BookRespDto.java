package com.example.junit_prac.dto;

import com.example.junit_prac.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class BookRespDto {

    private Long id;
    private String title;
    private String author;


    @Builder
    public BookRespDto(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
