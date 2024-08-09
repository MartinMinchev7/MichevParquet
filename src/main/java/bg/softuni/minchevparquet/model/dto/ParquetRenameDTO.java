package bg.softuni.minchevparquet.model.dto;

import jakarta.validation.constraints.NotBlank;

public class ParquetRenameDTO {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
