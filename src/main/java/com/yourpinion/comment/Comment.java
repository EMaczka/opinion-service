package com.yourpinion.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@ToString
@Getter
@Setter
@Entity
public class Comment {

    @EmbeddedId
    private CommentId pk;
    @Column(length = 1250)
    private String text;
}
