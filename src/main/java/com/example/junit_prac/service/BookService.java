package com.example.junit_prac.service;


import com.example.junit_prac.dto.BookRespDto;
import com.example.junit_prac.dto.BookSaveReqDto;
import com.example.junit_prac.entity.Book;
import com.example.junit_prac.entity.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    //1.책등록
    @Transactional
    public BookRespDto postBook(BookSaveReqDto dto) {
        Book bookPS = bookRepository.save(dto.toEntity());
        return new BookRespDto().toDto(bookPS);
    }
        //2.책목록보기
        public List<BookRespDto>책목록보기(){
            return bookRepository.findAll().stream()
                    .map(new BookRespDto()::toDto)
                    .collect(Collectors.toList());
        }

        //3.책한건보기

        //4.책삭제

        //5.책수정
    }

