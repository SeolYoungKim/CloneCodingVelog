package ToyProject.CloneCodingVelog.web.edit;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class EditForm {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    public EditForm() {
    }

    public EditForm(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
