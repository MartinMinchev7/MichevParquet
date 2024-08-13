package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.enums.PadModelName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AddPadDTO {

   @NotNull
   @Enumerated(EnumType.STRING)
   private PadModelName padModelName;
   @NotNull
   @Positive
   private Integer size;
   @NotNull
   @Positive
   private Double price;
   @NotBlank
   private String imageUrl;

   public AddPadDTO() {
   }

   public @NotNull PadModelName getPadModelName() {
       return padModelName;
   }

   public void setPadModelName(@NotNull PadModelName padModelName) {
       this.padModelName = padModelName;
   }

   public @NotNull PadModelName getPadModel() {
       return padModelName;
   }

   public void setPadModel(@NotNull PadModelName padModelName) {
       this.padModelName = padModelName;
   }

   public @Positive Integer getSize() {
       return size;
   }

   public void setSize(@Positive Integer size) {
       this.size = size;
   }

   public @Positive Double getPrice() {
       return price;
   }

   public void setPrice(@Positive Double price) {
       this.price = price;
   }

   public @NotBlank String getImageUrl() {
       return imageUrl;
   }

   public void setImageUrl(@NotBlank String imageUrl) {
       this.imageUrl = imageUrl;
   }

}
