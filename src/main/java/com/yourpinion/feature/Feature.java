package com.yourpinion.feature;

import com.yourpinion.comment.Comment;
import com.yourpinion.product.Product;
import com.yourpinion.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Feature {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Status status;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pk.feature")
    private Set<Comment> comments = new HashSet<>();
}
