package spring.library.domain;


//유저 1: 책 n 관계로 이루어짐

import jakarta.persistence.*;
import lombok.*;

import spring.library.dto.userDto.UserCreateRequest;
import spring.library.dto.userDto.UserUpdateRequest;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long idNumber;

    @Column(nullable = false)
    private String feature;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Library> libraryList;



    public static User user_from(UserCreateRequest userCreateRequest){
        return builder()
                .id(userCreateRequest.getId())
                .name(userCreateRequest.getName())
                .idNumber(userCreateRequest.getIdNumber())
                .feature(userCreateRequest.getFeature())
                .email(userCreateRequest.getEmail())
                .phoneNumber(userCreateRequest.getPhoneNumber())
                .build();
    }

    public void userUpdate(UserUpdateRequest request) {
        this.name = request.getName();
        this.idNumber = request.getIdNumber();
        this.feature = request.getFeature();
        this.email = request.getEmail();
        this.phoneNumber = request.getPhoneNumber();
    }
}
