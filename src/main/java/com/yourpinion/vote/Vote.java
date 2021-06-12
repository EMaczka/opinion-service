package com.yourpinion.vote;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@ToString
@Getter
@Setter
@Entity
public class Vote {

    @EmbeddedId
    private VoteId pk;
    private Boolean upvote;

}
