package ToyProject.CloneCodingVelog.domain.repository.series;

import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;

public interface SeriesJpaRepositoryCustom {
    SeriesEntity findBySeries(String series);
}
