package com.yourpinion.vote;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@ToString
@Getter
@Setter
@Builder
@Entity
public class Vote {

    @EmbeddedId
    private VoteId pk;
    private Boolean upvote;

}
