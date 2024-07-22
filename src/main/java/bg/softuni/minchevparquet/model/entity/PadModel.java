package bg.softuni.minchevparquet.model.entity;

import bg.softuni.minchevparquet.model.enums.PadModelName;
import jakarta.persistence.*;

@Entity
@Table(name = "pad_models")
public class PadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PadModelName padModelName;

    public PadModel(PadModelName modelName) {
        this.padModelName = modelName;
    }

    public PadModel() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PadModelName getPadModelName() {
        return padModelName;
    }

    public void setPadModelName(PadModelName padModelName) {
        this.padModelName = padModelName;
    }
}
