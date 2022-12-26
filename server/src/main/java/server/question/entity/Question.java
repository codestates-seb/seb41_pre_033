package server.question.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.answer.entity.Answer;
import server.tag.entity.Tag;
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
    @Column(name = "QUESTION_ID", nullable = false)
    private Long questionId;

    @Column
    private String title;

    @Column
    private String body;

    @Column
    private int bounty;

    @Column
    private LocalDateTime created;

    @Column
    private int viewed;

    @Column
    private int vote;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionTag> questionTags = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public void addQuestionTags(QuestionTag questionTag) {
        this.questionTags.add(questionTag);
        if(questionTag.getQuestion() != this){
            questionTag.addQuestion(this);
        }
    }

    public void addAnswers(Answer answer) {
        answers.add(answer);
        if(answer.getQuestion() != this){
            answer.addQuestion(this);
        }
    }

    //    user 에 follow 기능 추가 시 구현
//    @ManyToOne
//    @JoinColumn(name = "following_user_id")
//    private User followingUser;
}
