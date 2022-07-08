package ToyProject.CloneCodingVelog.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "series")
public class SeriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long series_id;

    @Column
    private String series;

    @OneToMany(mappedBy = "seriesEntity") // 한개가 다수로 매핑됨. (시리즈는 1개, 그에 해당하는 글이 여러개)
    private List<ArticleEntity> articles = new ArrayList<>();
}
