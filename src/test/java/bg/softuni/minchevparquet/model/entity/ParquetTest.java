package bg.softuni.minchevparquet.model.entity;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParquetTest {
    @Test
    public void testParquetEntityGettersAndSetters() {
        // Arrange
        Parquet parquet = new Parquet();
        Model model = new Model(); // Assuming Model class is correctly defined elsewhere
        long id = 1L;
        String name = "Oak";
        int size = 10;
        int classRate = 5;
        double price = 99.99;
        String imageUrl = "oak.jpg";

        // Act
        parquet.setId(id);
        parquet.setName(name);
        parquet.setModel(model);
        parquet.setSize(size);
        parquet.setClassRate(classRate);
        parquet.setPrice(price);
        parquet.setImageUrl(imageUrl);

        // Assert
        assertThat(parquet.getId()).isEqualTo(id);
        assertThat(parquet.getName()).isEqualTo(name);
        assertThat(parquet.getModel()).isEqualTo(model);
        assertThat(parquet.getSize()).isEqualTo(size);
        assertThat(parquet.getClassRate()).isEqualTo(classRate);
        assertThat(parquet.getPrice()).isEqualTo(price);
        assertThat(parquet.getImageUrl()).isEqualTo(imageUrl);
    }

    @Test
    public void testParquetEntityDefaultConstructor() {
        // Act
        Parquet parquet = new Parquet();

        // Assert
        assertThat(parquet.getId()).isZero();
        assertThat(parquet.getName()).isNull();
        assertThat(parquet.getModel()).isNull();
        assertThat(parquet.getSize()).isZero();
        assertThat(parquet.getClassRate()).isZero();
        assertThat(parquet.getPrice()).isZero();
        assertThat(parquet.getImageUrl()).isNull();
    }
}
