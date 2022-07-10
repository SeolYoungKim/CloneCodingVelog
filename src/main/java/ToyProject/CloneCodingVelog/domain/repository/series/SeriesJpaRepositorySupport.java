package ToyProject.CloneCodingVelog.domain.repository.series;

import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static ToyProject.CloneCodingVelog.domain.entity.QSeriesEntity.seriesEntity;

@Repository
public class SeriesJpaRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public SeriesJpaRepositorySupport(JPAQueryFactory queryFactory) {
        super(SeriesEntity.class);
        this.queryFactory = queryFactory;
    }

    public SeriesEntity findBySeries(String series) {
        return queryFactory
                .selectFrom(seriesEntity)
                .where(seriesEntity.series.eq(series))
                .fetchOne();
    }
}
