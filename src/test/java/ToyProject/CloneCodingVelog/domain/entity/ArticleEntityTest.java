package ToyProject.CloneCodingVelog.domain.entity;

import ToyProject.CloneCodingVelog.domain.repository.article.ArticleJpaRepository;
import ToyProject.CloneCodingVelog.domain.repository.article.ArticleJpaRepositorySupport;
import ToyProject.CloneCodingVelog.domain.repository.series.SeriesJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ArticleEntityTest {

    @Autowired
    ArticleJpaRepository articleJpaRepository;

    @Autowired
    SeriesJpaRepository seriesJpaRepository;

    @Autowired
    ArticleJpaRepositorySupport articleJpaRepositorySupport;

    @Test
    @DisplayName("관계 매핑이 제대로 되었는지 확인해본다.")
    @Transactional
    void relationalTest() {
        //given
        SeriesEntity series = SeriesEntity.builder()
                .series("시리즈1")
                .articles(new ArrayList<>())
                .build();

        ArticleEntity articleEntity1 = ArticleEntity.builder()
                .title("제목1")
                .text("본문1")
                .seriesEntity(series)
                .build();

        ArticleEntity articleEntity2 = ArticleEntity.builder()
                .title("제목2")
                .text("본문2")
                .seriesEntity(series)
                .build();

        //when
        seriesJpaRepository.save(series);
        articleJpaRepository.save(articleEntity1);
        articleJpaRepository.save(articleEntity2);

        List<ArticleEntity> articles = series.getArticles();
        articles.add(articleEntity1);
        articles.add(articleEntity2);

        //then
        assertThat(articleEntity1.getSeriesEntity()).isEqualTo(series);
        assertThat(articleEntity1.getSeriesEntity()).isEqualTo(articleEntity2.getSeriesEntity());

        assertThat(articles.size()).isEqualTo(2);
        assertThat(articles.get(0)).isEqualTo(articleEntity1);
    }

    @Test
    @DisplayName("시리즈로 글 찾기")
    @Transactional
    void findArticleBySeries() {
        //given
        SeriesEntity series = SeriesEntity.builder()
                .series("시리즈1")
                .articles(new ArrayList<>())
                .build();

        ArticleEntity articleEntity1 = ArticleEntity.builder()
                .title("제목1")
                .text("본문1")
                .seriesEntity(series)
                .build();

        ArticleEntity articleEntity2 = ArticleEntity.builder()
                .title("제목2")
                .text("본문2")
                .seriesEntity(series)
                .build();

        //when
        seriesJpaRepository.save(series);
        articleJpaRepository.save(articleEntity1);
        articleJpaRepository.save(articleEntity2);

        //then
        List<ArticleEntity> bySeries = articleJpaRepositorySupport.findBySeries(series);  // support로 조회.
        assertThat(bySeries.size()).isEqualTo(2);
        assertThat(bySeries.get(0)).isEqualTo(articleEntity1);

    }
}