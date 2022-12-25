package server.question.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.question.entity.Question;
import server.question.entity.QuestionTag;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {
    Page<Question> findAllByTagNameIgnoreCase(String tagName, Pageable pageable);

}
