package spring.library.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring.library.Repository.BookRepository;
import spring.library.Repository.LibraryRepository;
import spring.library.Repository.UserRepository;
import spring.library.domain.Book;
import spring.library.domain.Library;
import spring.library.domain.User;
import spring.library.dto.libraryDto.LibraryHistoryResponse;
import spring.library.dto.libraryDto.LibraryRequest;
import spring.library.dto.libraryDto.LibraryResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;
    private final UserRepository userRepository;


    @Transactional
    public String ckBook(long bookId, LibraryRequest libraryRequest) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        User user = userRepository.findById(libraryRequest.getMemberId()).orElseThrow();

        if (!book.getStatus().equals("대출가능")) {
            long count = user.getLibraryList().stream()
                    .filter(lib -> !lib.isReturned())
                    .count();
            switch (user.getFeature()) {
                case "학생":
                    if (count >= 10) return "대여량이 꽉찼습니다.";
                    break;
                case "교직원":
                    if (count >= 20) return "대여량이 꽉찼습니다.";
                    break;
                case "관리자":
                    if (count >= 100) return "대여량이 꽉찼습니다.";
                    break;
            }

            libraryRepository.save(Library.calDueDate(user, book));
            book.statusChange();
            return "대출 완료"; // 성공 시 반환
        }
        else return "안됨";
    }


    public List<LibraryResponse> loanList(long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getLibraryList().stream().filter(loan -> !loan.isReturned())
                .map(LibraryResponse::libararyResponseFrom).toList();
    }

    public List<LibraryResponse> loanHistoryList(long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getLibraryList().stream().map(LibraryHistoryResponse::from).toList();
    }

    @Transactional
    public String returnedBook(long bookId, LibraryRequest libraryRequest) {
        User user = userRepository.findById(libraryRequest.getMemberId()).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();
        List<Library> library = libraryRepository.findByBookAndUserAndReturned(book, user, false);
        book.statusChange();

        return "반납완료";
    }

    @Transactional
    public String renewalBook(long bookdId, LibraryRequest libraryRequest) {
        User user = userRepository.findById(libraryRequest.getMemberId()).orElseThrow();
        Book book = bookRepository.findById(bookdId).orElseThrow();
        Library library = libraryRepository.findByBookAndUserAndReturned(book, user, false)
                .stream()
                .findFirst()
                .orElseThrow();
        if(library.renewable()) {
            library.renew();
            return "연장 완료";
        }
        else return "연장 실패";
    }





}
