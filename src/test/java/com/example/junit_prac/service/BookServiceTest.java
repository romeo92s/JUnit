package com.example.junit_prac.service;

import com.example.junit_prac.dto.BookRespDto;
import com.example.junit_prac.dto.BookSaveReqDto;
import com.example.junit_prac.entity.Book;
import com.example.junit_prac.entity.BookRepository;
import com.example.junit_prac.util.MailSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

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
        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());
    }

    @Test
    public void 책목록보기_테스트(){
        //given(파라미터로 들어올 데이터)

        //stub(가설)
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L,"JUnit","허니통통"));
        books.add(new Book(2L,"JUnit5","이재헌"));

        when(bookRepository.findAll()).thenReturn(books);


        //when
        List<BookRespDto> bookRespDtoList = bookService.책목록보기();

        //print


        //then
        assertThat(bookRespDtoList.get(0).getTitle()).isEqualTo("JUnit");
        assertThat(bookRespDtoList.get(0).getAuthor()).isEqualTo("허니통통");
        assertThat(bookRespDtoList.get(1).getTitle()).isEqualTo("JUnit5");
        assertThat(bookRespDtoList.get(1).getAuthor()).isEqualTo("이재헌");
    }

    @Test
    public void 책한건보기_테스트(){
        //given
        Long id =1L;
        //stub
        Book book = new Book(1L,"JUnit","허니통통");
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP);

        //when
        BookRespDto bookRespDto = bookService.책한건보기(id);

        //then
        assertThat(bookRespDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(book.getAuthor());


    }
    @Test
    public void 책수정하기_테스트(){
        //given
        Long id = 1L;
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("JUnit5");
        dto.setAuthor("이재헌");
        //stub
        Book book = new Book(1L,"JUnit","허니통통");
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP);
        //when
        BookRespDto bookRespDto = bookService.책수정하기(id, dto);

        //then
        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());

    }
}
