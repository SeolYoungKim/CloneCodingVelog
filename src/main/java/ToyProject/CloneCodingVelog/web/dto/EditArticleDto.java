package ToyProject.CloneCodingVelog.web.dto;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.repository.ArticleJpaRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter @Setter
public class EditArticleDto {

    ArticleJpaRepository articleJpaRepository;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    private String series;

    public ArticleEntity toEntity(Long id) {
        return ArticleEntity.builder()
                .id(id)
                .title(this.title)
                .text(this.text)
                .series(this.series)
                .build();
    }
}
