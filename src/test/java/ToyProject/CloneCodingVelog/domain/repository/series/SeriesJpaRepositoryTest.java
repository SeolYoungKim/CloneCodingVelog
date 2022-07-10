package ToyProject.CloneCodingVelog.domain.repository.series;

import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeriesJpaRepositoryTest {

    @Autowired
    SeriesJpaRepository seriesJpaRepository;

    @Test
    @Transactional
    void seriesJpaRepoTest() {
        //given
        SeriesEntity series = SeriesEntity.builder()
                .series("목록1")
                .build();

        seriesJpaRepository.save(series);

        //when
        SeriesEntity bySeries = seriesJpaRepository.findBySeries("목록1");

        //then
        assertThat(bySeries).isEqualTo(series);
        assertThat(bySeries.getSeries()).isEqualTo("목록1");
    }

}