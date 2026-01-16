package spring.library.dto.libraryDto;


import lombok.Builder;
import lombok.Data;
import spring.library.domain.Library;

@Data
@Builder

public class LibararyResponse {
    private Long id;

    private Long bookId;

    private String title;

    private String author;

    private String loanDate;

    private String dueDate;

    private long renewalCount;


    private static LibararyResponse libararyResponseFrom(Library library) {
        return builder()
                .id(library.getId())
                .bookId(library.getBook().getId())
                .title(library.getBook().getTitle())
                .author(library.getBook().getAuthor())
                .loanDate(String.valueOf(library.getLoanDate()))
                .dueDate(String.valueOf(library.getDueDate()))
                .renewalCount(library.getRenewalCount())
                .build();
    }
}
