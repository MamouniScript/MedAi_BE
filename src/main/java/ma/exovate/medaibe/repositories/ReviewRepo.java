package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Long> {
}
