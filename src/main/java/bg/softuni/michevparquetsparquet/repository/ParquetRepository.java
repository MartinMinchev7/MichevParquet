package bg.softuni.michevparquetsparquet.repository;

import bg.softuni.michevparquetsparquet.model.entity.Parquet;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParquetRepository extends JpaRepository<Parquet, Long> {
    Optional<Parquet> findByName (String name);
    Optional<Parquet> findAllByModelModelName (ModelName modelName);
}
