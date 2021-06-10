package com.yourpinion.vote;

import com.yourpinion.feature.Feature;
import com.yourpinion.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class VoteId implements Serializable {

    private static final long serialVersionUID = 1234L;
    @ManyToOne
    private User user;
    @ManyToOne
    private Feature feature;
}
