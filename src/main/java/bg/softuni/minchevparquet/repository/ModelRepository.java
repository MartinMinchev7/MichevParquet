package bg.softuni.minchevparquet.repository;

import bg.softuni.minchevparquet.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByModelName(String name);
}
