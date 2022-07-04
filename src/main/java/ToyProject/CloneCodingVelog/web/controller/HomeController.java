package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import ToyProject.CloneCodingVelog.domain.repository.repositoryImpl.MemoryRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleRepository articleRepository;

    //TODO: 최신 글이 위로 오도록 해줘야 함.
    @GetMapping("/")
    public String home(Model model) {
        if (articleRepository != null) {
            List<Article> findArticles = articleRepository.findAll();
            model.addAttribute("articles", findArticles);
        }
//        else {  //TODO: 글이 없을 때 안내가 안나옴.
//            model.addAttribute("articles", null);
//        }
        return "home";
    }
}
