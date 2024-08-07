package bg.softuni.minchevparquet.init;

import bg.softuni.minchevparquet.model.entity.Model;
import bg.softuni.minchevparquet.model.entity.PadModel;
import bg.softuni.minchevparquet.model.enums.ModelName;
import bg.softuni.minchevparquet.model.enums.PadModelName;
import bg.softuni.minchevparquet.repository.ModelRepository;
import bg.softuni.minchevparquet.repository.PadModelRepository;
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
    private final PadModelRepository padModelRepository;

    public InitService(ModelRepository modelRepository, PadModelRepository padModelRepository) {
        this.modelRepository = modelRepository;
        this.padModelRepository = padModelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = this.modelRepository.count();
        long padModelCount = this.padModelRepository.count();

        if (count > 0 && padModelCount > 0) {
            return;
        }

        List<Model> list = Arrays.stream(ModelName.values())
                .map(modelName -> new Model(modelName, descriptions.get(modelName)))
                .toList();
        List<PadModel> padModels = Arrays.stream((PadModelName.values()))
                .map(PadModel::new)
                .toList();

        this.padModelRepository.saveAll(padModels);
        this.modelRepository.saveAll(list);
    }
}
