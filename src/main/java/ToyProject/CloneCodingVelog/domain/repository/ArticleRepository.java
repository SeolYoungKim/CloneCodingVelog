package ToyProject.CloneCodingVelog.domain.repository;

import ToyProject.CloneCodingVelog.domain.writing.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository {

    Article save(Article article);  // 리포지토리에 저장.

    Optional<Article> findById(Long id);  // uri 등으로 id 값이 넘어왔을 때 글 조회.

    List<Article> findAll();  // 모든 글 조회.

    //TODO: Title 조회 등.. 여러 추가해야 할 기능들이 많음.
}
