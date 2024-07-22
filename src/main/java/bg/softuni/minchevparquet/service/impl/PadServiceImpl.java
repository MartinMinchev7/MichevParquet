package bg.softuni.minchevparquet.service.impl;


import bg.softuni.minchevparquet.model.dto.AddPadDTO;
import bg.softuni.minchevparquet.model.dto.PadDetailsDTO;
import bg.softuni.minchevparquet.model.dto.PadSummaryDTO;
import bg.softuni.minchevparquet.model.entity.Pad;
import bg.softuni.minchevparquet.model.entity.PadModel;
import bg.softuni.minchevparquet.repository.PadModelRepository;
import bg.softuni.minchevparquet.repository.PadRepository;
import bg.softuni.minchevparquet.service.PadService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PadServiceImpl implements PadService {

    private final PadModelRepository padModelRepository;
    private final PadRepository padRepository;
    private final ModelMapper modelMapper;

    public PadServiceImpl(PadModelRepository padModelRepository, PadRepository padRepository, ModelMapper modelMapper) {
        this.padModelRepository = padModelRepository;
        this.padRepository = padRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createPad(AddPadDTO addPadDTO) {
        Optional<PadModel> byPadModelName = padModelRepository.findByPadModelName(addPadDTO.getPadModel());

        if (byPadModelName.isEmpty()) {
            throw new IllegalArgumentException("Model not found!");
        }

        Pad pad = modelMapper.map(addPadDTO, Pad.class);
        pad.setPadModel(byPadModelName.get());

        padRepository.save(pad);
    }

    @Override
    public List<PadSummaryDTO> getAllPadsSummary() {
        return padRepository.findAll()
                .stream()
                .map(pad -> modelMapper.map(pad, PadSummaryDTO.class))
                .toList();
    }

    @Override
    public PadDetailsDTO getPadDetails(Long id) {
        return padRepository
                .findById(id)
                .map(pad -> modelMapper.map(pad, PadDetailsDTO.class))
                .orElseThrow(() -> new IllegalStateException("Not found!"));
    }

    @Override
    public void deletePad(Long id) {
        padRepository.deleteById(id);
    }

}
