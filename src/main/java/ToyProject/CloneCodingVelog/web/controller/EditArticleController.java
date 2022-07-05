package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import ToyProject.CloneCodingVelog.domain.writing.edit.EditArticle;
import ToyProject.CloneCodingVelog.web.edit.EditForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
public class EditArticleController {

    private final ArticleRepository articleRepository;
    private final EditArticle editArticle;

    @GetMapping("article/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        if (id > articleRepository.findAll().size()) {
            throw new IllegalArgumentException("잘못된 요청");
        }

        Article findArticle = articleRepository.findById(id).orElse(null);

        model.addAttribute("editForm", findArticle);

        return "domain/editForm";
    }

    @PostMapping("article/{id}/edit")
    public String editArticle(@PathVariable Long id, @Validated @ModelAttribute EditForm editForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "domain/editForm";
        }

        if (id > articleRepository.findAll().size()) {
            throw new IllegalArgumentException("잘못된 요청");
        }

        editArticle.edit(id, editForm);

        return "redirect:/";
    }
}
