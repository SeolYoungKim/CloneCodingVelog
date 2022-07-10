package ToyProject.CloneCodingVelog.domain.repository.article;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ToyProject.CloneCodingVelog.domain.entity.QArticleEntity.articleEntity;

//TODO: 이게 왜 되는지 알기.. 아직 포린키와.. 등등.. 정확히 알지못한다..

//@Repository
public class ArticleJpaRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ArticleJpaRepositorySupport(JPAQueryFactory queryFactory) {
        super(ArticleEntity.class);
        this.queryFactory = queryFactory;
    }

    //TODO: join 쓰는 법 공부해서 join으로 바꾸자. 조인해서 검색해봐야지..
    public List<ArticleEntity> findBySeries(SeriesEntity series) {
        return queryFactory
                .selectFrom(articleEntity)
                .where(articleEntity.seriesEntity.eq(series))
                .fetch();
    }

}
