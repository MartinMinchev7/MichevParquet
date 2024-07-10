package bg.softuni.minchevparquet.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/news")
    public String news() {
        return "news";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
