package com.example.junit_prac.entity;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

    @Autowired //DI
    private BookRepository bookRepository;

    //    @BeforeAll   //테스트 시작전에 한번만 실행
    @BeforeEach  //각 테스트 시작전에 한번씩 실행
    public void 데이터준비() {
        String title = "JUnit";
        String author = "허니통통";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    }//트랜잭션 종료 됐다면 말이 안됨.
    //가정 1 : [데이터준비() + 1 책등록] (T), [데이터준비 () + 2 책목록보기](T) ,[데이터준비 () + 2 책한건보기](T) ( 이 검증이 맞음)
    //가정 2 : [데이터준비() + 1 책등록 + 데이터준비 () + 2 책목록보기](T) (검증 실패)

    //1.책등록
    @Test
    public void 책등록_test() {
        //given(데이터 준비)
        String title = "JUnit5";
        String author = "이재헌";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        //when(테스트 실행)
        Book bookPS = bookRepository.save(book); //퍼시스턴트(PS) >> 영구적으로 저장되었다

        //then(검증)
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    } //트랜잭션 종료 (테스트가 종료되면서 저장된 데이터를 초기화함)

    //2.책 목록보기
    @Test
    public void 책목록보기_test() {
        //given
        String title = "JUnit";
        String author = "허니통통";

        //when(테스트 실행)
//        Book bookPS = bookRepository.save(book);
        //when
        List<Book> booksPS = bookRepository.findAll();

        System.out.println("사이즈 검증 : ================================= : " +booksPS.size());
        //then
        assertEquals(title, booksPS.get(0).getTitle());
        assertEquals(author, booksPS.get(0).getAuthor());

    }

    //3.책 한건보기
    @Test
    public void 책한건보기_test() {
        //given
        String title = "JUnit";
        String author = "허니통통";

        //when

        Book bookPS = bookRepository.findById(1L).get();

        //then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    //4.책 수정

    //5.책 삭제

    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책삭제_test(){
        //given
        Long id = 1L;

        //when
        bookRepository.deleteById(id);
        //then
        assertFalse(bookRepository.findById(id).isPresent());
        }
    }

