package bg.softuni.michevparquetsparquet.model.entity;

import jakarta.persistence.*;

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

    private int size;

    private int classRate;

    private String imageUrl;

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
}

