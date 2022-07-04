package ToyProject.CloneCodingVelog.domain.repository.repositoryImpl;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryRepositoryTest {

    MemoryRepository repository = new MemoryRepository();

    @BeforeEach
    void clear() {
        repository.clear();
    }

    @Test
    void save() {
        Article article1 = new Article("제목입니다", "글입니다");

        Article savedArticle = repository.save(article1);

        assertThat(savedArticle).isEqualTo(article1);
        assertThat(savedArticle.getId()).isEqualTo(article1.getId());
        assertThat(savedArticle.getTitle()).isEqualTo(article1.getTitle());
        assertThat(savedArticle.getText()).isEqualTo(article1.getText());

    }

    @Test
    void findById() {
        Article article1 = new Article("제목입니다", "글입니다");
        Article article2 = new Article("제목임", "글임");

        repository.save(article1);
        repository.save(article2);

        Optional<Article> byId1 = repository.findById(1L);
        Optional<Article> byId2 = repository.findById(2L);

        assertThat(byId1.orElse(null)).isEqualTo(article1);
        assertThat(byId2.orElse(null)).isEqualTo(article2);

    }

    @Test
    void findAll() {
        Article article1 = new Article("제목입니다", "글입니다");
        Article article2 = new Article("제목임", "글임");

        repository.save(article1);
        repository.save(article2);

        List<Article> findAll = repository.findAll();

        assertThat(findAll.size()).isEqualTo(2);
    }
}