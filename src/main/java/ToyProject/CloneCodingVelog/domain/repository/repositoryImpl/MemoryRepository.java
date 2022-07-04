package ToyProject.CloneCodingVelog.domain.repository.repositoryImpl;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MemoryRepository implements ArticleRepository {

    Map<Long, Article> repository = new ConcurrentHashMap<>();
    Long sequence = 0L;

    @Override
    public Article save(Article article) {
        repository.put(++sequence, article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.of(repository.get(id));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(repository.values());
    }

    // Test용 코드
    public void clear() {
        repository.clear();
    }

    @PostConstruct
    public void init() {
        Article article1 = new Article("title", "text");
        Article article2 = new Article("title2", "text2");

        save(article1);
        save(article2);
    }
}
