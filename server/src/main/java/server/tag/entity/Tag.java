package server.tag.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag {
    @Id
    private String tagName;

    @Column(nullable = false)
    private String tagExplanation;
}
