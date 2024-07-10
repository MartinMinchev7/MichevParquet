package bg.softuni.minchevparquet.repository;

import bg.softuni.minchevparquet.model.entity.Parquet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParquetRepository extends JpaRepository<Parquet, Long> {
    boolean findByName(String name);

}
