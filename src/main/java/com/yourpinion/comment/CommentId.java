package com.yourpinion.comment;

import com.yourpinion.feature.Feature;
import com.yourpinion.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Embeddable
public class CommentId implements Serializable {

    private static final long serialVersionUID = 1234L;
    @ManyToOne
    private User user;
    @ManyToOne
    private Feature feature;
}
