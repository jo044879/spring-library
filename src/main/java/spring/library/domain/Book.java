package spring.library.domain;


import jakarta.persistence.*;
import lombok.*;
import spring.library.dto.bookDto.BookRequest;
import spring.library.dto.bookDto.BookResponse;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")


public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private Long publicationYear;

    private String classification;

    private String status;

    private Long amount;



    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Library> libraryList;

    public static Book bookFrom(BookRequest bookRequest) {
        return builder()
                .id(bookRequest.getId())
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .publicationYear(bookRequest.getPublicationYear())
                .classification(bookRequest.getClassification())
                .status(bookRequest.getStatus())
                .amount(bookRequest.getAmount())
                .build();
    }

    public void bookUpdate(BookRequest request) {
        this.title = request.getTitle();
        this.author = request.getAuthor();
        this.publisher = request.getPublisher();
        this.publicationYear = request.getPublicationYear();
        this.classification = request.getClassification();
        this.status = request.getStatus();
        this.amount = request.getAmount();
    }

    public void statusChange(){
        if(status.equals("대출가능")){
            status="대출중";
        }
        else status="대출가능";
    }

}
