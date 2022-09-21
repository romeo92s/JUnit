package com.example.junit_prac.service;


import com.example.junit_prac.dto.BookRespDto;
import com.example.junit_prac.dto.BookSaveReqDto;
import com.example.junit_prac.entity.Book;
import com.example.junit_prac.entity.BookRepository;
import com.example.junit_prac.util.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final MailSender mailSender;

    //1.책등록
    @Transactional
    public BookRespDto 책등록하기(BookSaveReqDto dto) {
        Book bookPS = bookRepository.save(dto.toEntity());
        if (bookPS != null) {
            if (!mailSender.send()) {
                throw new RuntimeException("메일이 전송되지 않았습니다.");
            }
        }
        return bookPS.toDto();
    }

    //2.책목록보기
    public List<BookRespDto> 책목록보기() {
        List<BookRespDto> dtos = bookRepository.findAll().stream()
//                    .map((bookPS) -> new BookRespDto().toDto(bookPS))
                .map(Book::toDto)
                .collect(Collectors.toList());

        return dtos;
    }

    //3.책한건보기
    public BookRespDto 책한건보기(Long id) {
        Optional<Book> bookOP = bookRepository.findById(id);
        if (bookOP.isPresent()) {
            Book bookPS = bookOP.get();
            return bookPS.toDto();
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }

    //4.책삭제
    @Transactional
    public void 책삭제하기(Long id) {
        bookRepository.deleteById(id);
    }

    //5.책수정하기
    @Transactional
    public BookRespDto 책수정하기(Long id, BookSaveReqDto dto) {
        Optional<Book> bookOP = bookRepository.findById(id);
        if (bookOP.isPresent()) {
            Book bookPS = bookOP.get();
            bookPS.update(dto.getTitle(), dto.getAuthor());
            return bookPS.toDto();
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }

    }

    //메서드 종료시에 더티체킹(fluiush)으로 update된다.


}