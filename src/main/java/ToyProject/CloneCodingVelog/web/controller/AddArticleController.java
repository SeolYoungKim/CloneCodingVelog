package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import ToyProject.CloneCodingVelog.web.add.AddForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/add")
@RequiredArgsConstructor
public class AddArticleController {

    private final ArticleRepository repository;

    @GetMapping
    public String addForm(Model model) {
        model.addAttribute(new AddForm());
        return "domain/addForm";
    }

    @PostMapping
    public String addArticle(@Validated @ModelAttribute AddForm addForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("error: {}", bindingResult);
            return "domain/addForm";
        }

        String title = addForm.getTitle();
        String text = addForm.getText();

        log.info("title: {}, text: {}", title, text);

        Article article = new Article(title, text);
        repository.save(article);

        return "redirect:/";
    }
}
