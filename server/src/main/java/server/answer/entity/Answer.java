package server.answer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.question.entity.Question;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
}
