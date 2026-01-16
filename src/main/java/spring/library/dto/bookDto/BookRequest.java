package spring.library.dto.bookDto;

import jakarta.persistence.Column;
import lombok.Data;


@Data

public class BookRequest {

    private Long id;

    private String title;

    private String author;

    private String publisher;

    private Long publicationYear;

    private String classification;

    private String status;

    private Long amount;
}
