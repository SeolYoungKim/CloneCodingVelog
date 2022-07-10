package ToyProject.CloneCodingVelog.domain.repository.article;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ToyProject.CloneCodingVelog.domain.entity.QArticleEntity.articleEntity;

@Repository
@RequiredArgsConstructor
public class ArticleJpaRepositoryImpl implements ArticleJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ArticleEntity> findBySeries(SeriesEntity series) {
        return queryFactory
                .selectFrom(articleEntity)
                .where(articleEntity.seriesEntity.eq(series))
                .fetch();
    }
}
