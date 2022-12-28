package server.answer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.question.entity.Question;
import server.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID", nullable = false)
    private Long answerId;

    @Column
    private String body;

    @Column
    private Boolean accepted;

    @Column
    private int vote;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public void addQuestion(Question question) {
        this.question = question;
        if (!this.question.getAnswers().contains(this)) {
            this.question.addAnswers(this);
        }
    }

    public void addUser(User user){
        this.user = user;
        if(!this.user.getAnswers().contains(this)){
            this.user.getAnswers().add(this);
        }
    }
}
