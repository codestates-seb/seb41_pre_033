package server.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.user.entity.UserTag;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {
}
