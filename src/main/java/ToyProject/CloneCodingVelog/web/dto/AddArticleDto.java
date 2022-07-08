package ToyProject.CloneCodingVelog.web.dto;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter @Setter
public class AddArticleDto {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    private SeriesEntity seriesEntity;

    public ArticleEntity toEntity() {
        return ArticleEntity.builder()
                .title(this.title)
                .text(this.text)
                .seriesEntity(this.seriesEntity)
                .build();
    }
}
