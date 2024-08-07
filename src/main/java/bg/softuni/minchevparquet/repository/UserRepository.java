package bg.softuni.minchevparquet.repository;

import bg.softuni.minchevparquet.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUuid (UUID uuid);

    Optional<User> findByFirstName(String firstName);
}
