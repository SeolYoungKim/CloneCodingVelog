package ToyProject.CloneCodingVelog.web.edit;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EditForm {

    private String title;
    private String text;

    public EditForm() {
    }

    public EditForm(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
