package ToyProject.CloneCodingVelog.web.dto;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import ToyProject.CloneCodingVelog.domain.repository.ArticleJpaRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class EditArticleDto {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    private SeriesEntity seriesEntity;
}
