package me.duhui.springbootdeveloper.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.duhui.springbootdeveloper.domain.Article;
import me.duhui.springbootdeveloper.dto.AddArticleRequest;
import me.duhui.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가하기
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
