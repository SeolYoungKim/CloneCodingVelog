package ToyProject.CloneCodingVelog.web.dto;

import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SeriesDto {

    private String series;

    public SeriesEntity toEntity() {
        return SeriesEntity.builder()
                .series(this.series)
                .build();
    }
}
