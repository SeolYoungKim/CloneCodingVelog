package ToyProject.CloneCodingVelog.domain.entity;

import ToyProject.CloneCodingVelog.domain.repository.ArticleJpaRepository;
import ToyProject.CloneCodingVelog.domain.repository.SeriesJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleEntityTest {

    @Autowired
    ArticleJpaRepository articleJpaRepository;

    @Autowired
    SeriesJpaRepository seriesJpaRepository;

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
                .title("제목1")
                .text("본문1")
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
    @DisplayName("관계 매핑이 제대로 되었는지 확인해본다.")
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
                .title("제목1")
                .text("본문1")
                .seriesEntity(series)
                .build();

        //when
        seriesJpaRepository.save(series);
        articleJpaRepository.save(articleEntity1);
        articleJpaRepository.save(articleEntity2);

        //then

    }

}