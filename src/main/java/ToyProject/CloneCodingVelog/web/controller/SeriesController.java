package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import ToyProject.CloneCodingVelog.domain.repository.article.ArticleJpaRepository;
import ToyProject.CloneCodingVelog.domain.repository.series.SeriesJpaRepository;
import ToyProject.CloneCodingVelog.web.dto.SeriesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SeriesController {

    private final ArticleJpaRepository articleJpaRepository;
    private final SeriesJpaRepository seriesJpaRepository;

    @GetMapping("/add-series")
    public String addSeriesForm(Model model) {
        model.addAttribute("seriesDto", new SeriesDto());
        return "domain/addSeries";
    }

    @PostMapping("/add-series")
    public String addSeries(
            @Validated @ModelAttribute SeriesDto seriesDto,
            BindingResult bindingResult,
            @RequestParam(defaultValue = "add") String type,
            @RequestParam(defaultValue = "0") Long id) {

        if (bindingResult.hasErrors()) {
            return "domain/addSeries";
        }

        seriesJpaRepository.save(seriesDto.toEntity());

        if (type.equals("add"))
            return "redirect:/" + type;

        return "redirect:/article/" + id + "/" + type;

    }

    @GetMapping("/series")
    public String seriesForm(Model model) {
        List<SeriesEntity> seriesList = seriesJpaRepository.findAll();
        model.addAttribute("seriesList", seriesList);
        return "web/seriesForm";
    }

    @GetMapping("/series/{id}")
    public String intoSeriesForm(@PathVariable Long id, Model model) {
        SeriesEntity series = seriesJpaRepository.findById(id).orElse(null);
        List<ArticleEntity> articleList = articleJpaRepository.findBySeries(series);
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
