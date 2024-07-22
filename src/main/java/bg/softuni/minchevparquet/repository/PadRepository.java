package bg.softuni.minchevparquet.repository;

import bg.softuni.minchevparquet.model.entity.Pad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PadRepository extends JpaRepository<Pad, Long> {
}
