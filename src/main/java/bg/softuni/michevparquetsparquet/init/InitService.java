package bg.softuni.michevparquetsparquet.init;

import bg.softuni.michevparquetsparquet.model.entity.Model;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;
import bg.softuni.michevparquetsparquet.repository.ModelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class InitService implements CommandLineRunner {

    private final Map<ModelName, String> descriptions = Map.of(
            ModelName.CLASSIC, "Classic parquet is a timeless wood flooring style characterized by its intricate geometric patterns, often using contrasting shades and types of wood.",
            ModelName.VINYL, "Vinyl parquet is a modern flooring option that replicates the classic look of traditional wood parquet.",
            ModelName.THREE_LAYERED, "Three-layered parquet is a high-quality wood flooring option composed of three layers: a top layer of solid hardwood, a core layer for stability, and a bottom layer for balance.",
            ModelName.LAMINATE, "Laminate parquet is a versatile and cost-effective flooring option that mimics the look of traditional wood parquet.",
            ModelName.CARPET_TILES, "Carpet tiles are modular flooring solutions composed of square pieces of carpet. They offer versatile design options, easy installation, and simple maintenance."
    );
    private final ModelRepository modelRepository;

    public InitService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = this.modelRepository.count();

        if (count > 0) {
            return;
        }

        List<Model> list = Arrays.stream(ModelName.values())
                .map(modelName -> new Model(modelName, descriptions.get(modelName)))
                .toList();

        this.modelRepository.saveAll(list);
    }
}
