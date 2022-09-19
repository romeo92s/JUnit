package com.example.junit_prac.entity;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

    @Autowired //DI
    private BookRepository bookRepository;

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
    }

    //2.책 목록보기

    //3.책 한건보기

    //4.책 수정

    //5.책 삭제    `
}
