package spring.library.dto.userDto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter

public class UserUpdateRequest {

    @NotBlank(message = "이름은 필수")
    private String name;

    private Long idNumber;

    @NotBlank(message = "학생 교직원인지 입력 바랍니다.")
    private String feature;

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "전화번호 필수 000-0000-0000 형식으로 입력바람.")
    private String phoneNumber;

}
