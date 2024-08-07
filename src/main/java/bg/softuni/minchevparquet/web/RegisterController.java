package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;
import bg.softuni.minchevparquet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerData", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO data,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !data.getConfirmPassword().equals(data.getPassword())) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegisterDTO", bindingResult);
            return "redirect:/users/register";
        }

        userService.registerUser(data);

        return "redirect:/users/login";
    }
}
