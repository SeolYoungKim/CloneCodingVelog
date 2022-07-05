package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(("/article/{id}/delete"))
@RequiredArgsConstructor
public class DeleteArticleController {
    private final ArticleRepository articleRepository;

    @GetMapping
    public String deleteForm(@PathVariable Long id, Model model) {
        //TODO: id 검증 로직이 3회 이상 반복되고 있다. 반복을 줄일 방법을 찾아보자.
        if (!articleRepository.hasId(id)) {
            throw new IllegalArgumentException("잘못된 요청");
        }

        model.addAttribute("id", id);

        return "domain/deleteArticle";
    }

    @PostMapping
    public String deleteArticle(@PathVariable Long id) {
        if (!articleRepository.hasId(id)) {
            throw new IllegalArgumentException("잘못된 요청");
        }

        articleRepository.delete(id);

        return "redirect:/";
    }
}
