package bg.softuni.michevparquetsparquet.model.entity;

import bg.softuni.michevparquetsparquet.model.enums.ModelName;
import jakarta.persistence.*;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ModelName modelName;

    @Column(nullable = false)
    private String description;

    public Model() {
    }

    public Model(ModelName name, String description) {
        this();

        this.modelName = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ModelName getModelName() {
        return modelName;
    }

    public void setModelName(ModelName modelName) {
        this.modelName = modelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

