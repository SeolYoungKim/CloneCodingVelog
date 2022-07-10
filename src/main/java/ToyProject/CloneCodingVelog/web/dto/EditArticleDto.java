package ToyProject.CloneCodingVelog.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class EditArticleDto {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

}
