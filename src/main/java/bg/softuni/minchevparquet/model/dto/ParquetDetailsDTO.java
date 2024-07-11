package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.entity.Model;

public record ParquetDetailsDTO(Long id,
                                String name,
                                Model model,
                                Integer size,
                                Integer classRate,
                                String imageUrl) {
}
