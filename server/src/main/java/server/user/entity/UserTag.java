package server.user.entity;

import server.tag.entity.Tag;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserTag {
    @Id
    private long userTagId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "TAG_NAME")
    private Tag tag;
}
