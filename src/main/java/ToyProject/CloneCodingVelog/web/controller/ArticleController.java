package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.repository.ArticleJpaRepository;
import ToyProject.CloneCodingVelog.web.dto.AddArticleDto;
import ToyProject.CloneCodingVelog.web.dto.EditArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleJpaRepository articleJpaRepository;

    @GetMapping("/")
    public String jpaHome(Model model) {
        List<ArticleEntity> all = articleJpaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("articles", all);
        return "home";
    }

    @GetMapping("/article/{id}")
    public String readArticle(@PathVariable Long id, Model model) {
        ArticleEntity article = articleJpaRepository.findById(id).orElse(null);
        model.addAttribute("article", article);
        return "domain/articleForm";
    }

    @GetMapping("add")
    public String addDto(Model model) {  // th:object를 사용하고 있어서, 빈 객체라도 보내줘야 함.
        model.addAttribute("addForm", new AddArticleDto());
        return "domain/addForm";
    }

    @PostMapping("add")
    public String addArticle(
            @Validated @ModelAttribute(name = "addForm") AddArticleDto addArticleDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "domain/addForm";
        }

        articleJpaRepository.save(addArticleDto.toEntity());

        return "redirect:/";
    }

    @GetMapping("/article/{id}/edit")
    public String editDto(@PathVariable Long id, Model model) {
        ArticleEntity article = articleJpaRepository.findById(id).orElse(null);
        model.addAttribute("editForm", article);
        return "domain/editForm";
    }

    @PostMapping("/article/{id}/edit")
    public String editArticle(
            @PathVariable Long id,
            @Validated @ModelAttribute(name = "editForm") EditArticleDto editArticleDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "domain/editForm";
        }

        // id로 엔티티를 찾는다.
        ArticleEntity findArticle = articleJpaRepository.findById(id).orElse(null);

        // 엔티티가 널이 아니면, 엔티티 필드를 editArticleDto의 필드로 교체한다.
        if (findArticle != null) {
            findArticle.editArticle(
                    editArticleDto.getTitle(),
                    editArticleDto.getText(),
                    editArticleDto.getSeriesEntity());

            articleJpaRepository.save(findArticle);
        }

        return "redirect:/";
    }

    @GetMapping("/article/{id}/delete")
    public String deleteForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "domain/deleteArticle";
    }

    @PostMapping("/article/{id}/delete")
    public String deleteArticle(@PathVariable Long id) {
        articleJpaRepository.findById(id).ifPresent(articleJpaRepository::delete);
        return "redirect:/";
    }
}
