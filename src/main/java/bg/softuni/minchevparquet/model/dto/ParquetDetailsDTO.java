package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.entity.Model;

public record ParquetDetailsDTO(Long id,
                                String name,
                                Model model,
                                Integer size,
                                Integer classRate,
                                Double price,
                                String imageUrl) {

    public static ParquetDetailsDTO empty() {
        return new ParquetDetailsDTO(null, null, null,null, null, null, null);
    }
}
