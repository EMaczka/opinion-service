package com.yourpinion.comment;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Comment {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1250)
    private String text;
    @ManyToOne
    @JsonIgnore
    private User user;
    @ManyToOne
    @JsonIgnore
    private Feature feature;
    @OneToMany(mappedBy = "comment")
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    @JsonIgnore
    private Comment comment;
    private Date createdDate;

}
