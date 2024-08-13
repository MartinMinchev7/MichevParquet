package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.enums.ModelName;
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
    @NotNull
    @Positive
    private Integer size;
    @NotNull
    @Positive
    private Integer classRate;
    @NotNull
    @Positive
    private Double price;
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

    public @Positive Double getPrice() {
        return price;
    }

    public void setPrice(@Positive Double price) {
        this.price = price;
    }
}
