package com.yourpinion.security;

import com.yourpinion.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 1234L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
    @ManyToOne
    private User user;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
