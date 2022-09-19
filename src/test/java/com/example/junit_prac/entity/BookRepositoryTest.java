package com.example.junit_prac.entity;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

    @Autowired //DI
    private BookRepository bookRepository;

    @BeforeAll
    public void 데이터준비(){

    }

    //1.책등록
    @Test
    public void 책등록_test(){
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
    public void 책목록보기_test(){
        //given
        String title = "JUnit5";
        String author = "이재헌";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
        //when(테스트 실행)
//        Book bookPS = bookRepository.save(book);
        //when
        List<Book> booksPS =bookRepository.findAll();
        //then
        assertEquals(title, booksPS.get(0).getTitle());
        assertEquals(author, booksPS.get(0).getAuthor());

    }

    //3.책 한건보기
    @Test
    public void 책한건보기_test(){
        //given
        String title = "JUnit5";
        String author = "이재헌";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
        //when

        Book bookPS = bookRepository.findById(1L).get();

        //then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    //4.책 수정

    //5.책 삭제    `
}
