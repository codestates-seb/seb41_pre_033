package server.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.answer.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
