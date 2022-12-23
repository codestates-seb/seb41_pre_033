package server.question.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.tag.entity.Tag;
import server.tag.entity.TagManager;
import server.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    private String title;

    private String body;

    private int bounty;

    private LocalDateTime created;

    private LocalDateTime modified;

    private int questionViews;

    private int questionVotes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "following_user_id")
    private User followingUser;

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private List<TagManager> tagManagers = new ArrayList<>();
}
