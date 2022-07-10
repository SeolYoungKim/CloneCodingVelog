package ToyProject.CloneCodingVelog.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginMemberDto {

    @NotBlank
    private String memberId;

    @NotBlank
    private String password;
}
