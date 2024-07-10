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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ParquetController {

    private final ParquetService parquetService;

    public ParquetController(ParquetService parquetService) {
        this.parquetService = parquetService;
    }


    @GetMapping("/parquet")
    public String parquet() {
        return "parquet";
    }

    @GetMapping("/add-parquet")
    public String addParquet(Model model) {

        if (!model.containsAttribute("parquetData")) {
            model.addAttribute("parquetData", AddParquetDTO.empty());
            model.addAttribute("models", ModelName.values());
        }

        return "add-parquet";
    }

    @PostMapping("/add-parquet")
    public String createParquet(@Valid AddParquetDTO addParquetDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("parquetData", addParquetDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addParquetDTO", bindingResult);
            return "redirect:/add-parquet";
        }

        boolean success = parquetService.createParquet(addParquetDTO);

        if (!success) {
            return "redirect:/add-parquet";
        }

        return "redirect:/";
    }

}
