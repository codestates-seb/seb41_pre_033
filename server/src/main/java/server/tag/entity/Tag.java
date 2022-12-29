package server.tag.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.question.entity.QuestionTag;
import server.user.entity.UserTag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    private String name;

    @Column(nullable = false)
    private String explanation;

    @Column
    private Integer used;

    @OneToMany(mappedBy = "tag",cascade = CascadeType.ALL)
    private List<QuestionTag> questionTags = new ArrayList<>();

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<UserTag> userTags = new ArrayList<>();

    public void addQuestionTag(QuestionTag questionTag) {
        this.questionTags.add(questionTag);
        if(questionTag.getTag() != this){
            questionTag.addTag(this);
        }
    }

    public void addUserTag(UserTag userTag) {
        this.userTags.add(userTag);
        if(userTag.getTag() != this) {
            userTag.addTag(this);
        }
    }
}
