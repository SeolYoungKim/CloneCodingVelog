package ToyProject.CloneCodingVelog.domain.writing.edit;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import ToyProject.CloneCodingVelog.web.edit.EditForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditArticle {

    private final ArticleRepository articleRepository;

    public Article edit(Long id, EditForm editForm) {
        Article originArticle = articleRepository.findById(id).orElse(null);

        if (originArticle != null) {
            originArticle.setTitle(editForm.getTitle());
            originArticle.setText(editForm.getText());
        }

        return originArticle;
    }
}
