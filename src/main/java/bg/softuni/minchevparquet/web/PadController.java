package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.dto.AddPadDTO;
import bg.softuni.minchevparquet.model.dto.PadSummaryDTO;
import bg.softuni.minchevparquet.model.dto.ParquetSummaryDTO;
import bg.softuni.minchevparquet.model.enums.PadModelName;
import bg.softuni.minchevparquet.service.exception.ObjectNotFoundException;
import bg.softuni.minchevparquet.service.impl.PadServiceImpl;
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
@RequestMapping("/pads")
public class PadController {

    private final PadServiceImpl padServiceImpl;

    public PadController(PadServiceImpl padServiceImpl) {
        this.padServiceImpl = padServiceImpl;
    }

    @ModelAttribute("allModelNames")
    public PadModelName[] allModels() {
        return PadModelName.values();
    }

    @GetMapping("/all")
    public String viewAllParquets(Model model) {
        List<PadSummaryDTO> pads = padServiceImpl.getAllPadsSummary();
        model.addAttribute("pads", pads);
        return "pads";
    }

    @GetMapping("/add")
    public String addPad(Model model) {

        if (!model.containsAttribute("padData")) {
            model.addAttribute("padData", new AddPadDTO());
            model.addAttribute("models", allModels());
        }

        return "add-pad";
    }

    @PostMapping("/add")
    public String createParquet(@Valid AddPadDTO addPadDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("padData", addPadDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.padData", bindingResult);
            return "redirect:/pads/add";
        }

        padServiceImpl.createPad(addPadDTO);

        return "redirect:/pads/all";
    }

    @GetMapping("/{id}")
    public String padDetails(@PathVariable("id") Long id, Model model) {

        model.addAttribute("padDetails", padServiceImpl.getPadDetails(id));

        return "pad-details";
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound(ObjectNotFoundException onfe) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("padId", onfe.getId());

        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePad(@PathVariable("id") Long id) {
        padServiceImpl.deletePad(id);

        return "redirect:/pads/all";
    }

}
