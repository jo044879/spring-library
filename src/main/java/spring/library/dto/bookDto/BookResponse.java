package spring.library.dto.bookDto;


import lombok.Builder;
import lombok.Data;
import spring.library.domain.Book;

@Data
@Builder

public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private String publisher;

    private Long publicationYear;

    private String classification;

    private String status;

    private Long amount;

    public static BookResponse bookResponseFrom(Book book) {
        return builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .classification(book.getClassification())
                .status(book.getStatus())
                .amount(book.getAmount())
                .build();
    }
}
