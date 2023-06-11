package me.duhui.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.duhui.springbootdeveloper.domain.Article;
import me.duhui.springbootdeveloper.dto.ArticleListViewResponse;
import me.duhui.springbootdeveloper.dto.ArticleViewResponse;
import me.duhui.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewContoller {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(Model model, @PathVariable long id) {
        Article article = blogService.findById(id);

        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
}
