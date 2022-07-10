package ToyProject.CloneCodingVelog.web.controller;

import ToyProject.CloneCodingVelog.domain.entity.ArticleEntity;
import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import ToyProject.CloneCodingVelog.domain.repository.article.ArticleJpaRepository;
import ToyProject.CloneCodingVelog.domain.repository.series.SeriesJpaRepository;
import ToyProject.CloneCodingVelog.domain.repository.series.SeriesJpaRepositorySupport;
import ToyProject.CloneCodingVelog.web.dto.AddArticleDto;
import ToyProject.CloneCodingVelog.web.dto.EditArticleDto;
import ToyProject.CloneCodingVelog.web.dto.SeriesDto;
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
    private final SeriesJpaRepository seriesJpaRepository;

    @ModelAttribute("seriesCategory")  // 시리즈 리포지토리의 시리즈를 목록화 후, 모델에 기본으로 저장.
    public List<SeriesEntity> seriesCategory() {
        return seriesJpaRepository.findAll();
    }

    @GetMapping("/")
    public String jpaHome(Model model) {
        List<ArticleEntity> all = articleJpaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        log.info("articles={}", all);
        model.addAttribute("articles", all);
        return "home";
    }

    @GetMapping("/article/{id}")
    public String readArticle(@PathVariable Long id, Model model) {
        ArticleEntity article = articleJpaRepository.findById(id).orElse(null);
        model.addAttribute("article", article);
        return "domain/articleForm";
    }

    @GetMapping("/add")
    public String addDto(Model model) {  // th:object를 사용하고 있어서, 빈 객체라도 보내줘야 함.
        model.addAttribute("addForm", new AddArticleDto());
        model.addAttribute("seriesForm", new SeriesDto());
        return "domain/addForm";
    }

    @PostMapping("/add")
    public String addArticle(
            @Validated @ModelAttribute(name = "addForm") AddArticleDto addArticleDto,
            BindingResult bindingResult,
            @ModelAttribute(name = "seriesForm") SeriesDto seriesDto
    ) {

        if (bindingResult.hasErrors()) {
            log.error("ERROR : {}", bindingResult);
            return "domain/addForm";
        }

        ArticleEntity article = addArticleDto.toEntity();

        // 시리즈 이름으로 시리즈 리포를 뒤져서 찾은 객체를 아티클에 저장해야 함.

        if (seriesDto != null) {
            SeriesEntity series = seriesJpaRepository.findBySeries(seriesDto.getSeries());

            if (series != null) {
                article.addSeries(series);  // article에 시리즈 객체 저장.
                series.addArticle(article);  // 시리즈의 리스트에 아티클 저장.
            }
        }

        articleJpaRepository.save(article);

        return "redirect:/";
    }

    @GetMapping("/article/{id}/edit")
    public String editDto(@PathVariable Long id, Model model) {
        ArticleEntity article = articleJpaRepository.findById(id).orElse(null);
        model.addAttribute("editForm", article);

        //TODO:article의 시리즈가 미리 널이 아님을 알 수는 없을까 ?
        if (article.getSeriesEntity() == null) {
            model.addAttribute("seriesForm", new SeriesDto());
        } else {
            model.addAttribute("seriesForm", article.getSeriesEntity());
        }

        return "domain/editForm";
    }

    @PostMapping("/article/{id}/edit")
    public String editArticle(
            @PathVariable Long id,
            @Validated @ModelAttribute(name = "editForm") EditArticleDto editArticleDto,
            BindingResult bindingResult,
            @ModelAttribute(name = "seriesForm") SeriesDto seriesDto
            ) {

        if (bindingResult.hasErrors()) {
            return "domain/editForm";
        }

        // id로 엔티티를 찾는다.
        ArticleEntity findArticle = articleJpaRepository.findById(id).orElse(null);

        // 엔티티가 널이 아니면, 엔티티 필드를 editArticleDto의 필드로 교체한다.
        if (findArticle != null && seriesDto != null) {
            //TODO: 해당 로직 간소화가 가능한지 알아보자.
            SeriesEntity series = seriesJpaRepository.findBySeries(seriesDto.getSeries());

            if (series != null) {
                findArticle.addSeries(series);  // article에 시리즈 객체 저장.
                series.addArticle(findArticle);  // 시리즈의 리스트에 아티클 저장.
            }

            findArticle.editArticle(
                    editArticleDto.getTitle(),
                    editArticleDto.getText(),
                    series);

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
