package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import ToyProject.CloneCodingVelog.domain.repository.ArticleJpaRepository;
import ToyProject.CloneCodingVelog.domain.repository.ArticleJpaRepositorySupport;
import ToyProject.CloneCodingVelog.domain.repository.SeriesJpaRepository;
import ToyProject.CloneCodingVelog.domain.repository.SeriesJpaRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SeriesController {

    private final ArticleJpaRepositorySupport articleJpaRepositorySupport;
    private final SeriesJpaRepository seriesJpaRepository;

    @GetMapping("/series")
    public String seriesForm(Model model) {
        List<SeriesEntity> seriesList = seriesJpaRepository.findAll();
        model.addAttribute("seriesList", seriesList);
        return "web/seriesForm";
    }

    @GetMapping("/series/{id}")
    public String intoSeriesForm(@PathVariable Long id, Model model) {
        SeriesEntity series = seriesJpaRepository.findById(id).orElse(null);
        List<ArticleEntity> articleList = articleJpaRepositorySupport.findBySeries(series);
        model.addAttribute("articleList", articleList);
        model.addAttribute("series", series);

        return "web/intoSeriesForm";
    }

    @GetMapping("/delete-series/{id}")
    public String deleteSeriesForm(@PathVariable Long id, @RequestParam(defaultValue = "") String type, Model model) {
        seriesJpaRepository.findById(id)
                .ifPresent(series -> model.addAttribute("series", series));

        if (type.equals("fail")) {
            model.addAttribute("type", type);
        }

        return "web/deleteSeries";
    }

    @PostMapping("/delete-series/{id}")
    public String deleteSeries(@PathVariable Long id) {
        SeriesEntity series = seriesJpaRepository.findById(id).orElse(null);

        if (series.getArticles().isEmpty()) {
            seriesJpaRepository.delete(series);
            return "redirect:/series";
        }

        return "redirect:/delete-series/" + id + "?type=fail";
    }
}
