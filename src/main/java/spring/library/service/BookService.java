package spring.library.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import spring.library.Repository.BookRepository;
import spring.library.domain.Book;
import spring.library.dto.bookDto.BookRequest;
import spring.library.dto.bookDto.BookResponse;

import java.util.List;

@Service
@RequiredArgsConstructor

@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse bookCreate(BookRequest bookRequest) {
        Book book = bookRepository.save(Book.bookFrom(bookRequest));
        return BookResponse.bookResponseFrom(book);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BookResponse> bookRead() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::bookResponseFrom)
                .toList();
    }
    @Transactional(Transactional.TxType.SUPPORTS)
    public BookResponse bookUpdate(BookRequest bookRequest, Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.bookUpdate(bookRequest);
        return BookResponse.bookResponseFrom(book);
    }

    public BookResponse bookDelete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.deleteById(id);
        return BookResponse.bookResponseFrom(book);
    }


}
