package spring.library.dto.userDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import spring.library.Repository.UserRepository;
import spring.library.domain.User;


@Data
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    public static UserResponse userResponseFrom(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .idNumber(user.getIdNumber())
                .feature(user.getFeature())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }


}
