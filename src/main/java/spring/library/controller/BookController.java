package spring.library.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.bookDto.BookRequest;
import spring.library.dto.bookDto.BookResponse;
import spring.library.service.BookService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books")

public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookResponse bookcreate(@RequestBody BookRequest bookRequest) {
        return bookService.bookCreate(bookRequest);
    }

    @GetMapping
    public List<BookResponse> bookread() {
        return bookService.bookRead();
    }

    @GetMapping("/{id}")
    public BookResponse bookUpdate(@RequestBody BookRequest bookRequest, @PathVariable Long id) {
        return bookService.bookUpdate(bookRequest, id);
    }

    @DeleteMapping("/{id}")
    public BookResponse bookDelete(@PathVariable Long id) {
        return bookService.bookDelete(id);
    }
}
