package server.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.image.entity.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
