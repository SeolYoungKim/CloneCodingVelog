package ToyProject.CloneCodingVelog.web.dto;

import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class SeriesDto {

    @NotBlank
    private String series;

    public SeriesEntity toEntity() {
        return SeriesEntity.builder()
                .series(this.series)
                .build();
    }
}
