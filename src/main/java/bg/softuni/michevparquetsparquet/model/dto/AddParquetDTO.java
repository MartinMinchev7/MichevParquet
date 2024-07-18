package bg.softuni.michevparquetsparquet.model.dto;

import bg.softuni.michevparquetsparquet.model.enums.ModelName;

public record AddParquetDTO(
        String name,
        ModelName modelName,
        Integer size,
        Integer classRate,
        Double price,
        String imageUrl
) {
}
