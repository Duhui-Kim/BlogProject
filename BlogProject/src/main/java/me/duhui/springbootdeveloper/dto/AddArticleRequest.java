package me.duhui.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.duhui.springbootdeveloper.domain.Article;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {

    private String title;
    private String content;
    public Article toEntity(String author) { // dto로 받은 객체를 Entity로 전환
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
