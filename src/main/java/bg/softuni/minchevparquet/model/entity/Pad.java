package bg.softuni.minchevparquet.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pads")
public class Pad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Integer size;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private PadModel padModel;

    @Column(nullable = false)
    private String imageUrl;

    public Pad() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
