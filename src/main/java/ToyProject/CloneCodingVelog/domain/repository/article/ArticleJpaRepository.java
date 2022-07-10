package ToyProject.CloneCodingVelog.domain.repository.article;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long>, ArticleJpaRepositoryCustom {
}
