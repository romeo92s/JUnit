package com.example.junit_prac.service;

import com.example.junit_prac.dto.BookRespDto;
import com.example.junit_prac.dto.BookSaveReqDto;
import com.example.junit_prac.entity.BookRepository;
import com.example.junit_prac.util.MailSenderStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    //문제점 -> 서비스만 테스트하고싶은데 레포지토리 레이어가 함께 테스트된다는 점.
    @Test
    public void 책등록하기_테스트(){

        //given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("JUnit");
        dto.setAuthor("이재헌");

        //stub
        MailSenderStub mailSenderStub =new MailSenderStub();
        //가짜로 bookRepository 만들기!

        //when
        BookService bookService = new BookService(bookRepository,mailSenderStub);
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        //then
        assertEquals(dto.getTitle(),bookRespDto.getTitle());
        assertEquals(dto.getAuthor(),bookRespDto.getAuthor());
    }
}
