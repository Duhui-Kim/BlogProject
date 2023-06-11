package me.duhui.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.duhui.springbootdeveloper.domain.Article;
import me.duhui.springbootdeveloper.dto.AddArticleRequest;
import me.duhui.springbootdeveloper.dto.ArticleResponse;
import me.duhui.springbootdeveloper.dto.UpdateArticleRequest;
import me.duhui.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(new ArticleResponse(updatedArticle));
    }
}
