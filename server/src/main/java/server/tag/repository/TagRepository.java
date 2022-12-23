package server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.tag.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
