package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.dto.UserRenameDTO;
import bg.softuni.minchevparquet.model.dto.UserLoginDTO;
import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView viewLogin() {
        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("loginData", new UserLoginDTO());

        return modelAndView;
    }

    @GetMapping("/login-error")
    public ModelAndView viewLoginError() {
        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("loginData", new UserLoginDTO());

        return modelAndView;
    }

    @GetMapping("/rename/{id}")
    public ModelAndView viewRename(@PathVariable Long id) {

        Optional<User> user = userService.getUserDetails(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        ModelAndView modelAndView = new ModelAndView("rename-user");

        modelAndView.addObject("renameData", new UserRenameDTO());
        modelAndView.addObject("userId", user.get().getId());

        return modelAndView;
    }

    @PatchMapping("/rename/{id}")
    public String renameUser(@PathVariable Long id,
                             @Valid UserRenameDTO userRenameDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("renameData", userRenameDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.renameDTO", bindingResult);
            return "redirect:/users/rename/" + id;
        }

        Optional<User> user = userService.getUserDetails(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        userService.renameUser(user.get(), userRenameDTO);

        return "redirect:/";
    }
}
