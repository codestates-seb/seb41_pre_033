package server.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.tag.entity.Tag;

import javax.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_TAG_ID", nullable = false)
    private Long userTagId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "TAG_NAME")
    private Tag tag;

    public void addUser(User user) {
        this.user = user;
        if (!this.user.getUserTags().contains(this)) {
            this.user.addUserTags(this);
        }
    }

    // TODO Tag 엔티티와 연결하고 나서 고려(인수님과 상의할 것)
    public void addTag(Tag tag) {
        this.tag = tag;
        if (!this.tag.getUserTags().contains(this)) {
            this.tag.addUserTag(this);
        }
    }
}
