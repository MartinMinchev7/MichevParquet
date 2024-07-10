package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.entity.Model;
import bg.softuni.minchevparquet.model.entity.ModelName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AddParquetDTO(
        @NotEmpty
        @Size(min = 2, max = 40)
        String name,
        @NotEmpty
        @Enumerated(EnumType.STRING)
        ModelName modelName,
        @NotEmpty
        @Positive
        Integer size,
        @NotEmpty
        @Positive
        Integer classRate,
        @NotEmpty
        String imageUrl

) {


    public static AddParquetDTO empty() {return new AddParquetDTO(null, null, null, null, null);}
}
