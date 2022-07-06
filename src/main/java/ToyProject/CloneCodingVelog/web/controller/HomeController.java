package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.repository.ArticleRepository;
import ToyProject.CloneCodingVelog.domain.repository.repositoryImpl.MemoryRepository;
import ToyProject.CloneCodingVelog.domain.writing.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ArticleRepository articleRepository;

    @GetMapping("/")
    public String home(Model model, HttpServletResponse response) {
        List<Article> findArticles = articleRepository.findAll();

        if (findArticles.isEmpty()) {
            return "home";
        }

        model.addAttribute("articles", findArticles);

        return "home";
    }

//    @GetMapping("/series")
//    public String series(Model model) {
//        //시리즈 이름이 뜨도록 해야한다.
//        //그러려면 등록된 시리즈 이름을 조회해야 한다.
//    }
}
