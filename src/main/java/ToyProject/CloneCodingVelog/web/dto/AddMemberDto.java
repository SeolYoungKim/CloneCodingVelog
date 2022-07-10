package ToyProject.CloneCodingVelog.web.dto;

import ToyProject.CloneCodingVelog.domain.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AddMemberDto {

    @NotBlank
    @Length(max = 10)
    private String memberId;

    @NotBlank
    @Length(min = 8, max = 20)
    private String password;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .memberId(this.memberId)
                .password(this.password)
                .build();
    }
}
