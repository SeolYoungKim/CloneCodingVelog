package ToyProject.CloneCodingVelog.domain.repository.repositoryImpl;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class MemoryRepository implements ArticleRepository {

    Map<Long, Article> repository = new ConcurrentHashMap<>();
    Long sequence = 0L;

    @Override
    public Boolean hasId(Long id) {
        return repository.containsKey(id);
    }

    @Override
    public Article save(Article article) {
        repository.put(++sequence, article);
        article.setId(sequence);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.of(repository.get(id));
    }

    /**
     * 해당 메서드는 최신 글이 위로 올라오도록 역순으로 구성하였다.
     * id 값에는 변동이 없으므로, 이렇게 구성해도 상관 없을 것이라고 판단하였다.
     */
    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>(repository.values());
        Collections.reverse(articles);
        return articles;
    }

    @Override
    public void delete(Long id) {
        repository.remove(id);
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

//        log.info("article1's id = {}", article1.getId());
//        log.info("article2's id = {}", article2.getId());
    }
}
