package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.dto.AddParquetDTO;
import bg.softuni.minchevparquet.model.dto.ParquetDetailsDTO;
import bg.softuni.minchevparquet.model.dto.ParquetSummaryDTO;
import bg.softuni.minchevparquet.model.entity.ModelName;
import bg.softuni.minchevparquet.service.ParquetService;
import bg.softuni.minchevparquet.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/parquets")
public class ParquetController {

    private final ParquetService parquetService;

    public ParquetController(ParquetService parquetService) {
        this.parquetService = parquetService;
    }

    @ModelAttribute("allModelNames")
    public ModelName[] allModelNames() {return ModelName.values();}


    @GetMapping("/parquet")
    public String parquet() {
        return "parquet";
    }

    @GetMapping("add")
    public String viewParquet(Model model) {

        if (!model.containsAttribute("parquetData")) {
            model.addAttribute("parquetData", new AddParquetDTO());
            model.addAttribute("models", allModelNames());
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

        return "redirect:/parquets/all";
    }

    @GetMapping("/{id}")
    public String offerDetails(@PathVariable("id") Long id, Model model) {

        model.addAttribute("parquetDetails", parquetService.getParquetDetails(id));

        return "details";
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound(ObjectNotFoundException onfe) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("parquetId", onfe.getId());

        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOffer(@PathVariable("id") Long id) {
        parquetService.deleteParquet(id);

        return "redirect:/parquets/all";
    }

    @GetMapping("/all")
    public String viewAllParquets(Model model) {
        List<ParquetSummaryDTO> parquets = parquetService.getAllParquetsSummary();
        model.addAttribute("parquets", parquets);
        return "parquets";
    }

//    @GetMapping("/vinyl")
//    public String viewAllVinylParquets(Model model) {
//        List<ParquetDetailsDTO> parquets = parquetService.getAllVinylParquets();
//        model.addAttribute("parquets", parquets);
//        return "parquets";
//    }

}
