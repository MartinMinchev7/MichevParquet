package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.dto.AddParquetDTO;
import bg.softuni.minchevparquet.model.dto.ParquetDetailsDTO;
import bg.softuni.minchevparquet.model.dto.ParquetRenameDTO;
import bg.softuni.minchevparquet.model.dto.ParquetSummaryDTO;
import bg.softuni.minchevparquet.model.enums.ModelName;
import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;
import bg.softuni.minchevparquet.service.ParquetService;
import bg.softuni.minchevparquet.service.UserService;
import bg.softuni.minchevparquet.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


    @GetMapping("/")
    public String parquet() {
        return "parquets";
    }

    @GetMapping("/all")
    public String viewAllParquets(Model model) {
        List<ParquetSummaryDTO> parquets = parquetService.getAllParquetsSummary();
        model.addAttribute("parquets", parquets);
        return "parquets";
    }

    @GetMapping("/add")
    public String viewParquet(Model model) {

        if (!model.containsAttribute("parquetData")) {
            model.addAttribute("parquetData", new AddParquetDTO());
            model.addAttribute("models", allModelNames());
        }

        return "add-parquet";
    }

    @PostMapping("/add")
    public String createParquet(@Valid AddParquetDTO addParquetDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("parquetData", addParquetDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.parquetData", bindingResult);
            return "redirect:/parquets/add";
        }

        parquetService.createParquet(addParquetDTO);

        return "redirect:/parquets/all";
    }

    @GetMapping("/{id}")
    public String parquetDetails(@PathVariable("id") Long id, Model model) {

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
    public String deleteParquet(@PathVariable("id") Long id) {
        parquetService.deleteParquet(id);

        return "redirect:/parquets/all";
    }

    @GetMapping("/rename/{id}")
    public String update(@PathVariable("id") Long id, Model model) {

        if (!model.containsAttribute("parquetRenameData")) {
            model.addAttribute("parquetRenameData", new ParquetRenameDTO());
        }

        return "change-parquet-name";
    }

    @PatchMapping("/rename/{id}")
    public String renameParquet(@PathVariable("id") Long id,
                                @Valid ParquetRenameDTO renameDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("parquetRenameData", renameDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.parquetRenameData", bindingResult);
            return "redirect:parquets/update/" + id;
        }

        parquetService.renameParquet(id, renameDTO);

        return "redirect:/parquets/all";
    }

    @GetMapping("/model/vinyl")
    public String viewAllVinylParquets(Model model) {
        model.addAttribute("parquetVinyl", parquetService.getAllVinylParquets());
        return "vinyl-parquet";
    }

    @GetMapping("/model/classic")
    public String viewAllClassicParquets(Model model) {
        model.addAttribute("parquetClassic", parquetService.getAllClassicParquets());
        return "classic-parquet";
    }

    @GetMapping("/model/three-layered")
    public String viewAllThreeLayeredParquets(Model model) {
        model.addAttribute("parquetThreeLayered", parquetService.getAllThreeLayeredParquets());
        return "three-layered-parquet";
    }

    @GetMapping("/model/laminate")
    public String viewAllLaminateParquets(Model model) {
        model.addAttribute("parquetLaminate", parquetService.getAllLaminateParquets());
        return "laminate-parquet";
    }

    @GetMapping("/model/carpet-tiles")
    public String viewAllCarpetTilesParquets(Model model) {
        model.addAttribute("parquetCarpetTiles", parquetService.getAllCarpetTilesParquets());
        return "carpet-tiles-parquet";
    }

}
