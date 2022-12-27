package server.question.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.tag.entity.Tag;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_TAG_ID", nullable = false)
    private Long questionTagId;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "TAG_NAME")
    private Tag tag;

    public void addQuestion(Question question){
        this.question = question;
        if(!this.question.getQuestionTags().contains(this)){
            this.question.addQuestionTags(this);
        }
    }

    public void addTag(Tag tag) {
        this.tag = tag;
        if(!this.tag.getQuestionTags().contains(this)){
            this.tag.addQuestionTag(this);
        }
    }
}
