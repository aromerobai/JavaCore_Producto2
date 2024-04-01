package org.javacoreuocx.alquilatusvehiculos.controller;

import org.javacoreuocx.alquilatusvehiculos.model.ContratoAlquiler;
import org.javacoreuocx.alquilatusvehiculos.model.Oficina;
import org.javacoreuocx.alquilatusvehiculos.repository.ContratoAlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/administracion")
public class AdminContratosAlquilerController {

    @Autowired
    private ContratoAlquilerRepository contratoAlquilerRepository;

    @GetMapping("/contratos-alquiler")
    public String listarContratosAlquiler(Model model) {
        model.addAttribute("contratosAlquiler", contratoAlquilerRepository.findAll());
        return "administracion/contratos-alquiler";
    }

    @GetMapping("/contratos-alquiler/nuevo")
    public String mostrarFormularioDeNuevaContratoAlquiler(Model model) {
        model.addAttribute("contratoAlquiler", new ContratoAlquiler());
        return "administracion/contratos-alquiler/edit";
    }

    @PostMapping("/contratos-alquiler/guardar")
    public String guardarContratoAlquiler(@ModelAttribute("contratoAlquiler") ContratoAlquiler contratoAlquiler, RedirectAttributes redirectAttributes) {
        contratoAlquilerRepository.save(contratoAlquiler);
        redirectAttributes.addFlashAttribute("mensaje", "Oficina guardada con éxito");
        return "redirect:/administracion/contratos-alquiler";
    }

    @GetMapping("/contratos-alquiler/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        ContratoAlquiler contratoAlquiler = contratoAlquilerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de oficina inválido:" + id));
        model.addAttribute("contratoAlquiler", contratoAlquiler);
        return "administracion/contratos-alquiler/edit";
    }
    @GetMapping("/contratos-alquiler/borrar/{id}")
    public String borrarContratoAlquiler(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ContratoAlquiler contratoAlquiler = contratoAlquilerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de oficina inválido:" + id));
        contratoAlquilerRepository.delete(contratoAlquiler);
        redirectAttributes.addFlashAttribute("mensaje", "Oficina borrada con éxito");
        return "redirect:/administracion/contratos-alquiler";
    }
}
