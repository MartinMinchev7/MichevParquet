package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.entity.ModelName;
import bg.softuni.minchevparquet.service.ParquetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
