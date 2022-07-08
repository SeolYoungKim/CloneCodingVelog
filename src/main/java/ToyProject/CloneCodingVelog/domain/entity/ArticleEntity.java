package ToyProject.CloneCodingVelog.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "ARTICLE")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column
    private String text;

    @ManyToOne  // 다수가 한개로 매핑됨. (글이 여러개, 해당되는 시리즈가 한개)
    @JoinColumn(name = "series_id")
    private SeriesEntity seriesEntity;

    public ArticleEntity(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
