package ToyProject.CloneCodingVelog.domain.writing.edit;

import ToyProject.CloneCodingVelog.domain.repository.repositoryImpl.MemoryRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import ToyProject.CloneCodingVelog.web.edit.EditForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class EditArticleTest {

    MemoryRepository repository = new MemoryRepository();
    EditArticle editArticle = new EditArticle(repository);

    @DisplayName("수정 기능이 올바르게 작동하는지")
    @Test
    void edit() {
        //given
        Article article = new Article("제목입니다.", "본문입니다.");
        repository.save(article);

        //when
        Article editedArticle = editArticle.edit(1L, new EditForm("제목이지롱", "본문이지롱"));

        //then
        assertThat(editedArticle.getId()).isEqualTo(article.getId());
        assertThat(article.getTitle()).isEqualTo("제목이지롱");
        assertThat(article.getText()).isEqualTo("본문이지롱");
    }
}