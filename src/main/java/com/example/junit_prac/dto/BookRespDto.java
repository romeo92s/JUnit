package com.example.junit_prac.dto;

import com.example.junit_prac.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class BookRespDto {

    private Long id;
    private String title;
    private String author;


    public  BookRespDto toDto(Book bookPS){
        this.id = bookPS.getId();
        this.title = bookPS.getTitle();
        this.author = bookPS.getAuthor();
        return this;
    }


}
