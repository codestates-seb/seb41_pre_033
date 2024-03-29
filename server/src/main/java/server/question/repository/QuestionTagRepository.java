package server.question.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.question.entity.Question;
import server.question.entity.QuestionTag;

@Repository
public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {
    Page<Question> findAllByTagNameIgnoreCase(String tagName, Pageable pageable);
    void deleteAllByQuestion(Question question);
}
