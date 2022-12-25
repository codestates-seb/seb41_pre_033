package server.tag.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.question.entity.QuestionTag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag {
    @Id
    private String name;

    @Column(nullable = false)
    private String explanation;

    @OneToMany(mappedBy = "tag")
    private List<QuestionTag> questionTags = new ArrayList<>();
}
