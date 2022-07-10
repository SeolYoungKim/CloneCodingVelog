package ToyProject.CloneCodingVelog.domain.repository.article;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryCustomTest {

    @Autowired
    private ArticleJpaRepository articleJpaRepository;

    @Test
    @Transactional
    void querydslCustomRepoTest() {
        //given
        SeriesEntity series = SeriesEntity.builder()
                .series("목록입니다.")
                .build();

        ArticleEntity article = ArticleEntity.builder()
                .title("제목입니다")
                .text("내용입니다.")
                .seriesEntity(series)
                .build();

        articleJpaRepository.save(article);

        //when
        List<ArticleEntity> bySeries = articleJpaRepository.findBySeries(series);

        //then
        assertThat(bySeries.size()).isEqualTo(1);
        assertThat(bySeries.get(0).getSeriesEntity().getSeries()).isEqualTo("목록입니다.");
    }

}