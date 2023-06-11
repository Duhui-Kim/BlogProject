package me.duhui.springbootdeveloper.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.duhui.springbootdeveloper.domain.Article;
import me.duhui.springbootdeveloper.dto.AddArticleRequest;
import me.duhui.springbootdeveloper.dto.UpdateArticleRequest;
import me.duhui.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가하기
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not fount: " + id));
    }

    @Transactional
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
