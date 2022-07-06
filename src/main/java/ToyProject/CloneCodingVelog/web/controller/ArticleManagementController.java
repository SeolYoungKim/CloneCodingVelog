package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import ToyProject.CloneCodingVelog.domain.writing.edit.EditArticle;
import ToyProject.CloneCodingVelog.web.edit.EditForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * uri가 어느정도 동일한 부분이 있는 것들끼리 합쳤음.
 * 조회, 수정, 삭제 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/article/{id}")
public class ArticleManagementController {

    private final ArticleRepository articleRepository;
    private final EditArticle editArticle;

    @GetMapping
    public String readArticle(@PathVariable Long id, Model model) {
        validateId(id);

        Optional<Article> findArticle = articleRepository.findById(id);
        Article article = findArticle.orElse(null);

        model.addAttribute("article", article);

        return "domain/articleForm";
    }

    @GetMapping("/edit")
    public String editForm(@PathVariable Long id, Model model) {
        validateId(id);

        Article findArticle = articleRepository.findById(id).orElse(null);

        model.addAttribute("editForm", findArticle);

        return "domain/editForm";
    }

    @PostMapping("/edit")
    public String editArticle(@PathVariable Long id, @Validated @ModelAttribute EditForm editForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "domain/editForm";
        }

        validateId(id);

        editArticle.edit(id, editForm);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteForm(@PathVariable Long id, Model model) {
        validateId(id);

        model.addAttribute("id", id);

        return "domain/deleteArticle";
    }

    @PostMapping("/delete")
    public String deleteArticle(@PathVariable Long id) {
        validateId(id);

        articleRepository.delete(id);

        return "redirect:/";
    }


    //TODO: 일단 메서드 추출로 해당 로직을 간소화했다. 이것을 클래스 단위로 빼는게 좋을지 고민해보자.
    private void validateId(Long id) {
        if (!articleRepository.hasId(id)) {
            throw new IllegalArgumentException("잘못된 요청");
        }
    }
}
