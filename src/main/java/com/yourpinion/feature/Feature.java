package com.yourpinion.feature;

import com.yourpinion.product.Product;
import com.yourpinion.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
