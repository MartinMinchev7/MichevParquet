package bg.softuni.michevparquetsparquet.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "parquets")
public class Parquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    private Model model;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private int classRate;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false, unique = true)
    private String imageUrl;

    @Column(nullable = false)
    private Instant created = Instant.now();

    public Parquet() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getClassRate() {
        return classRate;
    }

    public void setClassRate(int classRate) {
        this.classRate = classRate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public @NotNull Instant getCreated() {
        return created;
    }

    public void setCreated(@NotNull Instant created) {
        this.created = created;
    }
}

