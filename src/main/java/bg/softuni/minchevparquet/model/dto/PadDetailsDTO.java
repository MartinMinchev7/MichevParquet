package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.entity.PadModel;
import bg.softuni.minchevparquet.model.enums.PadModelName;

public class PadDetailsDTO {
    private Long id;
    private Integer size;
    private Double price;
    private PadModel padModel;
    private String imageUrl;

    public PadDetailsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PadModel getPadModel() {
        return padModel;
    }

    public void setPadModel(PadModel padModel) {
        this.padModel = padModel;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
