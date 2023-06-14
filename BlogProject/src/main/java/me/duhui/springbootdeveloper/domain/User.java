package me.duhui.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = List.of(new SimpleGrantedAuthority("user"));

        for(GrantedAuthority g : list) {
            System.out.println(g.toString());
        }

        return list;
    }
    // 사용자의 패스워드를 반환
    @Override
    public String getPassword() {
        return password;
    }
    // 사용자의 id를 반환 (고유한 값)
    @Override
    public String getUsername() {
        return email;
    }
    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true; // true => 만료되지 않음
    }
    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true; // true => 잠금되지 않음
    }
    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // true => 만료되지 않음
    }
    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true; // true => 사용 가능
    }
}