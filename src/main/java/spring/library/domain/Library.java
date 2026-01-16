package spring.library.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "library")

public class Library {


    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    private Book book;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loanDate;

    private String dueDate;

    private long renewalCount;

    private boolean returned;


    //대출 반납
    public static Library calDueDate(User user, Book book) {
        int day = switch (user.getFeature()) {
            case "학생" -> 10;
            case "교직원" -> 30;
            case "관리자" -> 110813;
            default -> 0;
        };
        return Library.builder()
                .loanDate(LocalDate.now().toString())
                .dueDate(LocalDate.now().plusDays(day).toString())
                .renewalCount(0)
                .returned(false)
                .build();
    }

    public void returnLoan() {
        returned = true;
    }

    public boolean renewable(){
        return renewalCount == 0;
    }

    public void renew() {
        dueDate = LocalDate.parse(dueDate, DateTimeFormatter.ISO_LOCAL_DATE).plusDays(5).toString();
        renewalCount++;
    }


}
