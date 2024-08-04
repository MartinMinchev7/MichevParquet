package bg.softuni.michevparquetsparquet.repository;

import bg.softuni.michevparquetsparquet.model.entity.Parquet;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParquetRepository extends JpaRepository<Parquet, Long> {
    Optional<Parquet> findByName (String name);
    List<Parquet> findAllByModelModelName (ModelName modelName);

    @Transactional
    @Modifying
    @Query("DELETE FROM Parquet p WHERE p.created < :olderThan")
    void deleteOldParquets(Instant olderThan);
}
