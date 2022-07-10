package ToyProject.CloneCodingVelog.domain.repository.series;

import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static ToyProject.CloneCodingVelog.domain.entity.QSeriesEntity.seriesEntity;

@Repository
@RequiredArgsConstructor
public class SeriesJpaRepositoryImpl implements SeriesJpaRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public SeriesEntity findBySeries(String series) {
        return queryFactory
                .selectFrom(seriesEntity)
                .where(seriesEntity.series.eq(series))
                .fetchOne();
    }
}
