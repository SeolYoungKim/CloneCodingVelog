package ToyProject.CloneCodingVelog.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//TODO: 얘도 빌더 수정해줘야지 뭐하니?

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

    @Builder.Default
    @OneToMany(mappedBy = "seriesEntity", cascade = CascadeType.PERSIST) // 한개가 다수로 매핑됨. (시리즈는 1개, 그에 해당하는 글이 여러개)
    private List<ArticleEntity> articles = new ArrayList<>();

    //setter를 쓰지 않기 위한 방안
    public void addArticle(ArticleEntity articleEntity) {
        this.articles.add(articleEntity);
    }
}
