package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.entity.Model;
import bg.softuni.minchevparquet.model.entity.ModelName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

public class AddParquetDTO {
    @NotBlank
    @Size(min = 2, max = 40)
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ModelName modelName;
    @Positive
    private Integer size;
    @Positive
    private Integer classRate;
    @Positive
    private Integer price;
    @NotBlank
    private String imageUrl;

   public AddParquetDTO() {
   }

   public @NotBlank @Size(min = 2, max = 40) String getName() {
           return name;
   }

   public void setName(@NotBlank @Size(min = 2, max = 40) String name) {
           this.name = name;
   }

   public @NotNull ModelName getModelName() {
           return modelName;
   }

   public void setModelName(@NotNull ModelName modelName) {
           this.modelName = modelName;
   }

   public @Positive Integer getSize() {
           return size;
   }

   public void setSize(@Positive Integer size) {
           this.size = size;
   }

   public @Positive Integer getClassRate() {
           return classRate;
   }

   public void setClassRate(@Positive Integer classRate) {
           this.classRate = classRate;
   }

   public @NotBlank String getImageUrl() {
           return imageUrl;
   }

   public void setImageUrl(@NotBlank String imageUrl) {
           this.imageUrl = imageUrl;
   }

    public @Positive Integer getPrice() {
        return price;
    }

    public void setPrice(@Positive Integer price) {
        this.price = price;
    }
}
