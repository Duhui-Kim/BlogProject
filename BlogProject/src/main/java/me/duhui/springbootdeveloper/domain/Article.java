package me.duhui.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter // getter만 열어둠
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자 생성
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    @Column(name = "id", updatable = false) // 수정 불가
    private Long id;

    @Column(name = "title", nullable = false) // not null
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder // 빌더 패턴으로 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
