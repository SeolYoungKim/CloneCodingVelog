package ToyProject.CloneCodingVelog.domain.repository;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<ArticleEntity> findBySeries(SeriesEntity series);
}
