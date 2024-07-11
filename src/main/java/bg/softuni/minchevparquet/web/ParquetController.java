package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.dto.AddParquetDTO;
import bg.softuni.minchevparquet.model.entity.ModelName;
import bg.softuni.minchevparquet.service.ParquetService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/parquets")
public class ParquetController {

    private final ParquetService parquetService;

    public ParquetController(ParquetService parquetService) {
        this.parquetService = parquetService;
    }


    @GetMapping("/parquet")
    public String parquet() {
        return "parquet";
    }

    @GetMapping("add")
    public String viewParquet(Model model) {

        if (!model.containsAttribute("parquetData")) {
            model.addAttribute("parquetData", new AddParquetDTO());
            model.addAttribute("models", ModelName.values());
        }

        return "add-parquet";
    }

    @PostMapping("add")
    public String createParquet(@Valid AddParquetDTO addParquetDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("parquetData", addParquetDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addParquetDTO", bindingResult);
            return "redirect:/add-parquet";
        }

        parquetService.createParquet(addParquetDTO);

        return "redirect:/";
    }

}
