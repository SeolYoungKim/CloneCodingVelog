package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReadArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping("/article/{id}")
    public String readArticle(@PathVariable Long id, Model model) {
        if (id > articleRepository.findAll().size()) {
            throw new IllegalArgumentException();
        }

        Optional<Article> findArticle = articleRepository.findById(id);
        Article article = findArticle.orElse(null);

        model.addAttribute("article", article);

        return "domain/articleForm";
    }
}
