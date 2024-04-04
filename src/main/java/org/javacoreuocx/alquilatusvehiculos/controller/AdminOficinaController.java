package org.javacoreuocx.alquilatusvehiculos.controller;

import org.javacoreuocx.alquilatusvehiculos.model.Oficina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.javacoreuocx.alquilatusvehiculos.repository.OficinaRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/administracion")
public class AdminOficinaController {

    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping("/oficinas")
    public String listarOficinas(Model model) {
        model.addAttribute("currentPage", "oficinas");
        model.addAttribute("oficinas", oficinaRepository.findAll());
        return "administracion/oficinas";
    }

    @GetMapping("/oficinas/nuevo")
    public String mostrarFormularioDeNuevaOficina(Model model) {
        model.addAttribute("currentPage", "oficina");
        model.addAttribute("oficina", new Oficina());
        return "administracion/oficinas/edit";
    }

    @PostMapping("/oficinas/guardar")
    public String guardarOficina(@ModelAttribute("oficina") Oficina oficina, RedirectAttributes redirectAttributes) {
        oficinaRepository.save(oficina);
        redirectAttributes.addFlashAttribute("mensaje", "Oficina guardada con éxito");
        return "redirect:/administracion/oficinas";
    }

    @GetMapping("/oficinas/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Integer id, Model model) {
        Oficina oficina = oficinaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de oficina inválido:" + id));
        model.addAttribute("currentPage", "oficina");
        model.addAttribute("oficina", oficina);
        return "administracion/oficinas/edit";
    }

    @GetMapping("/oficinas/borrar/{id}")
    public String borrarOficina(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Oficina oficina = oficinaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de oficina inválido:" + id));
        oficinaRepository.delete(oficina);
        redirectAttributes.addFlashAttribute("mensaje", "Oficina borrada con éxito");
        return "redirect:/administracion/oficinas";
    }
}
