package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.dto.AddPadDTO;
import bg.softuni.minchevparquet.model.dto.PadDetailsDTO;
import bg.softuni.minchevparquet.model.dto.PadSummaryDTO;
import bg.softuni.minchevparquet.model.entity.Pad;
import bg.softuni.minchevparquet.model.entity.PadModel;
import bg.softuni.minchevparquet.model.enums.PadModelName;
import bg.softuni.minchevparquet.repository.PadModelRepository;
import bg.softuni.minchevparquet.repository.PadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PadServiceImplTest {
    private PadServiceImpl toTest;

    @Captor
    private ArgumentCaptor<Pad> padCaptor;

    @Mock
    private PadModelRepository mockPadModelRepository;
    @Mock
    private PadRepository mockPadRepository;
    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    public void setUp() {
        toTest = new PadServiceImpl(mockPadModelRepository, mockPadRepository, mockModelMapper);
    }

    @Test
    void testCreatePadSuccess() {
        AddPadDTO addPad = new AddPadDTO();
        addPad.setPadModel(PadModelName.VILO);
        addPad.setSize(8);
        addPad.setPrice(22.2);
        addPad.setImageUrl("imageUrl");

        PadModel padModel = new PadModel();
        padModel.setPadModelName(PadModelName.VILO);

        Pad pad = new Pad();
        pad.setPadModel(padModel);
        pad.setSize(8);
        pad.setPrice(22.2);
        pad.setImageUrl("imageUrl");

        when(mockPadModelRepository.findByPadModelName(addPad.getPadModel()))
                .thenReturn(Optional.of(padModel));
        when(mockModelMapper.map(addPad, Pad.class)).thenReturn(pad);

        toTest.createPad(addPad);

        verify(mockPadRepository).save(padCaptor.capture());

        Pad actualSavedEntity = padCaptor.getValue();

        Assertions.assertEquals(padModel, actualSavedEntity.getPadModel());
        Assertions.assertEquals(addPad.getSize(), actualSavedEntity.getSize());
        Assertions.assertEquals(addPad.getPrice(), actualSavedEntity.getPrice());
        Assertions.assertEquals(addPad.getImageUrl(), actualSavedEntity.getImageUrl());
    }

    @Test
    public void testCreatePadModelNotFound() {
        AddPadDTO addPadDTO = new AddPadDTO();
        addPadDTO.setPadModel(null);

        when(mockPadModelRepository.findByPadModelName(addPadDTO.getPadModel())).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> toTest.createPad(addPadDTO));
    }

    @Test
    public void testGetAllPadsSummary() {
        Pad pad1 = new Pad();
        Pad pad2 = new Pad();
        List<Pad> pads = List.of(pad1, pad2);

        PadSummaryDTO padSummaryDTO1 = new PadSummaryDTO();
        PadSummaryDTO padSummaryDTO2 = new PadSummaryDTO();

        when(mockPadRepository.findAll()).thenReturn(pads);
        when(mockModelMapper.map(pad1, PadSummaryDTO.class)).thenReturn(padSummaryDTO1);
        when(mockModelMapper.map(pad2, PadSummaryDTO.class)).thenReturn(padSummaryDTO2);

        List<PadSummaryDTO> result = toTest.getAllPadsSummary();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(padSummaryDTO1, result.get(0));
        Assertions.assertEquals(padSummaryDTO2, result.get(1));
    }

    @Test
    public void testGetPadDetailsSuccess() {
        Long padId = 1L;
        Pad pad = new Pad();
        PadDetailsDTO padDetailsDTO = new PadDetailsDTO();

        when(mockPadRepository.findById(padId)).thenReturn(Optional.of(pad));
        when(mockModelMapper.map(pad, PadDetailsDTO.class)).thenReturn(padDetailsDTO);

        PadDetailsDTO result = toTest.getPadDetails(padId);

        Assertions.assertEquals(padDetailsDTO, result);
    }

    @Test
    public void testGetPadDetailsNotFound() {
        Long padId = 1L;

        when(mockPadRepository.findById(padId)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalStateException.class, () -> toTest.getPadDetails(padId));
    }

    @Test
    public void testDeletePad() {
        Long padId = 1L;

        toTest.deletePad(padId);

        verify(mockPadRepository, times(1)).deleteById(padId);
    }

}
