package bg.softuni.minchevparquet.repository;

import bg.softuni.minchevparquet.model.entity.Model;
import bg.softuni.minchevparquet.model.entity.PadModel;
import bg.softuni.minchevparquet.model.enums.ModelName;
import bg.softuni.minchevparquet.model.enums.PadModelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PadModelRepository extends JpaRepository<PadModel, Long> {
    Optional<PadModel> findByPadModelName (PadModelName padModelName);
}
