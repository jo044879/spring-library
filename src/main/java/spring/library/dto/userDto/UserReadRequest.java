package spring.library.dto.userDto;


import jakarta.persistence.Column;
import lombok.Data;

@Data

public class UserReadRequest {

    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;
}
