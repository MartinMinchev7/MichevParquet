package bg.softuni.minchevparquet.service;


import bg.softuni.minchevparquet.model.dto.AddPadDTO;
import bg.softuni.minchevparquet.model.dto.PadDetailsDTO;
import bg.softuni.minchevparquet.model.dto.PadSummaryDTO;

import java.util.List;

public interface PadService {
    void createPad(AddPadDTO addPadDTO);

    List<PadSummaryDTO> getAllPadsSummary();

    PadDetailsDTO getPadDetails(Long id);

    void deletePad(Long id);
}
