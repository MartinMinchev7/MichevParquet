package bg.softuni.michevparquetsparquet.model.dto;

import bg.softuni.michevparquetsparquet.model.entity.Model;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;

public record ParquetDTO(
        Long id,
        String name,
        Model model,
        Integer size,
        Integer classRate,
        Integer price,
        String imageUrl
) {
}
