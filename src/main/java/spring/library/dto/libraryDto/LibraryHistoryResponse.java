package spring.library.dto.libraryDto;


import lombok.Data;
import spring.library.domain.Library;

@Data

public class LibraryHistoryResponse {

    private long bookId;
    private String title;
    private String author;
    private String loanDate;
    private String dueDate;
    private long renewalCount;
    private boolean returned;

    public static LibraryResponse from(Library library) {
        return LibraryResponse.builder()
                .bookId(library.getBook().getId())
                .title(library.getBook().getTitle())
                .author(library.getBook().getAuthor())
                .loanDate(library.getLoanDate())
                .dueDate(library.getDueDate())
                .renewalCount(library.getRenewalCount())
                .build();
    }
}
