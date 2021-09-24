package com.yourpinion.comment;

import com.yourpinion.feature.Feature;
import com.yourpinion.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
public class Comment {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1250)
    private String text;
    @ManyToOne
    private User user;
    @ManyToOne
    private Feature feature;
    @OneToMany(mappedBy = "comment")
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    private Comment comment;
    private Date createdDate;

}
