package com.example.junit_prac.dto;

import com.example.junit_prac.entity.Book;
import lombok.Setter;

@Setter  //Controller에서 Setter가 호출되면서 Dto에 값이 채워짐.
public class BookSaveReqDto {

    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}
