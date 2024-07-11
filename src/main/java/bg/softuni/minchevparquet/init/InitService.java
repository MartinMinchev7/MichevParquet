package bg.softuni.minchevparquet.init;

import bg.softuni.minchevparquet.model.entity.Model;
import bg.softuni.minchevparquet.model.entity.ModelName;
import bg.softuni.minchevparquet.repository.ModelRepository;
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
