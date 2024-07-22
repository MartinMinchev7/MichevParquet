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
            ModelName.CLASSIC, "",
            ModelName.VINYL, "",
            ModelName.THREE_LAYERED, "",
            ModelName.LAMINATE, "",
            ModelName.CARPET_TILES, ""
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
