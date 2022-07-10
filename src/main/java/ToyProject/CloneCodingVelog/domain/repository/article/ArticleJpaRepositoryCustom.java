package ToyProject.CloneCodingVelog.domain.repository.article;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;

import java.util.List;

public interface ArticleJpaRepositoryCustom {
    List<ArticleEntity> findBySeries(SeriesEntity series);
}
