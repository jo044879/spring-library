package spring.library.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.libraryDto.LibraryRequest;
import spring.library.dto.libraryDto.LibraryResponse;
import spring.library.service.LibraryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LibraryController {

    public final LibraryService libraryService;

    @PostMapping("/books/bookId/checkout")
    public String ckBook(@PathVariable long id, @RequestBody LibraryRequest libraryRequest){
        return libraryService.ckBook(id, libraryRequest);
    }

    @GetMapping("/books/checkout?memberId={id}")
    public List<LibraryResponse> readBook(@PathVariable long id) {
        return libraryService.loanList(id);
    }

    @GetMapping("/books/history?memberId={id}")
    public List<LibraryResponse> historyBook(@PathVariable long id) {
        return libraryService.loanHistoryList(id);
    }

    @PutMapping("/books/:bookId/return")
    public String returnBook(@PathVariable long bookId, @RequestBody LibraryRequest libraryRequest) {
        return libraryService.returnedBook(bookId, libraryRequest);
    }

    @PutMapping
    public String renewalBook(@PathVariable long id, @RequestBody LibraryRequest libraryRequest) {
        return libraryService.renewalBook(id, libraryRequest);
    }







}
