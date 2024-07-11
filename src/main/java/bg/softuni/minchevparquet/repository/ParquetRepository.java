package bg.softuni.minchevparquet.repository;

import bg.softuni.minchevparquet.model.entity.Parquet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParquetRepository extends JpaRepository<Parquet, Long> {
    Optional<Parquet> findByName(String name);

}
