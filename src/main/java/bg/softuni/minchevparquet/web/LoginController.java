package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.dto.RenameDTO;
import bg.softuni.minchevparquet.model.dto.UserLoginDTO;
import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;
import bg.softuni.minchevparquet.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

        ModelAndView modelAndView = new ModelAndView("rename");

        modelAndView.addObject("renameData", new RenameDTO());
        modelAndView.addObject("userId", user.get().getId());

        return modelAndView;
    }

    @PostMapping("/rename/{id}")
    public String renameUser(@RequestBody RenameDTO renameDTO,
                                    @PathVariable Long id) {

        Optional<User> user = userService.getUserDetails(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        userService.renameUser(user.get(), renameDTO);

        return "redirect:/";
    }
}
