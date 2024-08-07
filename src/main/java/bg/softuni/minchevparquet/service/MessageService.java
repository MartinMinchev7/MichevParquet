package bg.softuni.minchevparquet.service;

import bg.softuni.minchevparquet.model.dto.AddMessageDTO;
import bg.softuni.minchevparquet.model.entity.Message;

public interface MessageService {
    void addMessage(AddMessageDTO addMessageDTO);
}
