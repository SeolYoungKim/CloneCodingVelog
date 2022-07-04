package ToyProject.CloneCodingVelog.domain.writing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 글 객체
 */
@Getter
@RequiredArgsConstructor
public class Article {

    @NotNull
    private Long id;  // id는 나중에 세팅해준다. (저장할 때)

    @NotBlank
    private final String title;

    @NotBlank
    private final String text;


}
