package spring.library.dto.libraryDto;


import lombok.Data;
import spring.library.domain.Library;

@Data

public class LibararyReturnResponse {

    private long bookId;
    private String title;
    private String author;
    private String loanDate;
    private String dueDate;
    private long renewalCount;
    private boolean returned;

    public static LibararyReturnResponse from(Library library) {
        return LibararyResponse
    }
}
