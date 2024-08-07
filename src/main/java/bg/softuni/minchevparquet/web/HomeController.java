package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;
import bg.softuni.minchevparquet.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        Optional<MinchevParquetUserDetails> user = userService.getCurrentUser();

        if (user.isEmpty()) {
            throw new IllegalStateException("User not found");
        }

        Long userId = user.get().getId();
        model.addAttribute("userId", userId);
        return "index";
    }

}
