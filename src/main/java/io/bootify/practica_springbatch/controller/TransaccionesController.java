package io.bootify.practica_springbatch.controller;

import io.bootify.practica_springbatch.model.TransaccionesDTO;
import io.bootify.practica_springbatch.service.TransaccionesService;
import io.bootify.practica_springbatch.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/transaccioness")
public class TransaccionesController {

    private final TransaccionesService transaccionesService;

    public TransaccionesController(final TransaccionesService transaccionesService) {
        this.transaccionesService = transaccionesService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("transaccioneses", transaccionesService.findAll());
        return "transacciones/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("transacciones") final TransaccionesDTO transaccionesDTO) {
        return "transacciones/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("transacciones") @Valid final TransaccionesDTO transaccionesDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "transacciones/add";
        }
        transaccionesService.create(transaccionesDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("transacciones.create.success"));
        return "redirect:/transaccioness";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("transacciones", transaccionesService.get(id));
        return "transacciones/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("transacciones") @Valid final TransaccionesDTO transaccionesDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "transacciones/edit";
        }
        transaccionesService.update(id, transaccionesDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("transacciones.update.success"));
        return "redirect:/transaccioness";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        transaccionesService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("transacciones.delete.success"));
        return "redirect:/transaccioness";
    }

}
