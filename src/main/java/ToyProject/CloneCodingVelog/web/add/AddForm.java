package ToyProject.CloneCodingVelog.web.add;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AddForm {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    public AddForm() {
    }
}
