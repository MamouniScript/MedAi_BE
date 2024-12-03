package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.authEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}