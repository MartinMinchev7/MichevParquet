package bg.softuni.minchevparquet.model.dto;

import jakarta.validation.constraints.NotNull;

public class MakeAdminDTO {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
