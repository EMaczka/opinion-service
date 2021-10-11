package com.yourpinion.comment;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yourpinion.feature.Feature;
import com.yourpinion.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Comment implements Comparable<Comment> {

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
    @OrderBy("createdDate, id")
    private SortedSet<Comment> comments = new TreeSet<>();
    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    @JsonIgnore
    private Comment comment;
    private Date createdDate;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comment other = (Comment) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
    @Override
    public String toString() {
        return "Comment [id=" + id + ", text=" + text + "]";
    }
    @Override
    public int compareTo(Comment that) {
        int comparedValue = this.createdDate.compareTo(that.createdDate);
        if (comparedValue == 0) {
            comparedValue = this.id.compareTo(that.id);
        }
        return comparedValue;
    }
}
