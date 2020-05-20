package kr.co.prekore.springboot.config.auth.dto;

import kr.co.prekore.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
