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

    @Column
    private String series;

    public ArticleEntity(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
