package server.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.answer.entity.Answer;
import server.question.entity.Question;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String country;

    @Column
    private Integer reputation;

    @Column
    private String title;

    @Column
    private String introduction;

    @Column
    private String link;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTag> userTags = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    // TODO Answer는 Question을 거쳐서 꺼낼 수 있는가? 아니면 Answer도 따로 연관 관계 표시를 해야 할까? -> 일단은 answer도 가지고 있자.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public void addUserTags(UserTag userTag) {
        this.userTags.add(userTag);
        if(userTag.getUser() != this){
            userTag.addUser(this);
        }
    }
}
