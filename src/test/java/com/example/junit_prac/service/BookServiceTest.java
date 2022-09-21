package com.example.junit_prac.service;

import com.example.junit_prac.dto.BookRespDto;
import com.example.junit_prac.dto.BookSaveReqDto;
import com.example.junit_prac.entity.BookRepository;
import com.example.junit_prac.util.MailSender;
import com.example.junit_prac.util.MailSenderStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //가짜 레이어드환경이 만들어짐
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private MailSender mailSender;

    //문제점 -> 서비스만 테스트하고싶은데 레포지토리 레이어가 함께 테스트된다는 점.
    @Test
    public void 책등록하기_테스트(){

        //given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("JUnit");
        dto.setAuthor("이재헌");

        //stub
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        //when
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        //then
//        assertEquals(dto.getTitle(),bookRespDto.getTitle());
//        assertEquals(dto.getAuthor(),bookRespDto.getAuthor());
        assertThat(dto.getTitle()).isEqualTo(bookRespDto.getTitle());
        assertThat(dto.getAuthor()).isEqualTo(bookRespDto.getAuthor());


    }
}
