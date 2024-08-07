package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.dto.AddMessageDTO;
import bg.softuni.minchevparquet.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ModelAttribute("messageData")
    public AddMessageDTO addMessageDTO() {
        return new AddMessageDTO();
    }

    @GetMapping("#section_contact")
    public String contact(Model model) {

        if (model.containsAttribute("messageData")) {
            model.addAttribute("messageData", new AddMessageDTO());
        }

        return "index";
    }

    @PostMapping("#section_contact")
    public String sendMessage(@Valid AddMessageDTO addMessageDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("messageData", addMessageDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addMessageDTO", bindingResult);
            return "redirect:#section_contact";
        }

        messageService.addMessage(addMessageDTO);
        return "redirect:/";
    }
}
