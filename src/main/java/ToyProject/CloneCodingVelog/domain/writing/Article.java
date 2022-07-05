package ToyProject.CloneCodingVelog.domain.writing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 글 객체
 */
@Getter @Setter
public class Article {

    @NotNull
    private Long id;  // id는 나중에 세팅해준다. (저장할 때)

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
