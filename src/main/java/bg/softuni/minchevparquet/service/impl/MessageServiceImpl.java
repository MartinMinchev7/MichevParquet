package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.dto.AddMessageDTO;
import bg.softuni.minchevparquet.model.entity.Message;
import bg.softuni.minchevparquet.repository.MessageRepository;
import bg.softuni.minchevparquet.repository.UserRepository;
import bg.softuni.minchevparquet.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void addMessage(AddMessageDTO addMessageDTO) {
        String sender = addMessageDTO.getSender();

        if (userRepository.findByFirstName(sender).isEmpty()) {
            throw new IllegalArgumentException("No such sender");
        }

        Message map = modelMapper.map(addMessageDTO, Message.class);
        map.setSender(userRepository.findByFirstName(sender).get());

        messageRepository.save(map);
    }
}
