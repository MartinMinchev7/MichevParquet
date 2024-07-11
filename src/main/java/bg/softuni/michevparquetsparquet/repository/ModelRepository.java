package bg.softuni.michevparquetsparquet.repository;

import bg.softuni.michevparquetsparquet.model.entity.Model;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByModelName (ModelName modelName);
}
