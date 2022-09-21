package com.example.junit_prac.service;


import com.example.junit_prac.dto.BookRespDto;
import com.example.junit_prac.dto.BookSaveReqDto;
import com.example.junit_prac.entity.Book;
import com.example.junit_prac.entity.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {

//    private final BookRepository bookRepository;
//
//    //1.책등록
//    public BookRespDto postBook(BookSaveReqDto dto){
//        Book bookPS = bookRepository.save(dto.toEntity());
//        return bookPS;
//    }
}
