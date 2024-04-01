package org.javacoreuocx.alquilatusvehiculos.controller;

import org.javacoreuocx.alquilatusvehiculos.model.Oficina;
import org.javacoreuocx.alquilatusvehiculos.model.Vehiculo;
import org.javacoreuocx.alquilatusvehiculos.repository.OficinaRepository;
import org.javacoreuocx.alquilatusvehiculos.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/administracion")
public class AdminVehiculoController {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping("/vehiculos")
    public String listarVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "administracion/vehiculos";
    }

    @GetMapping("/vehiculos/nuevo")
    public String mostrarFormularioDeNuevaVehiculo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "administracion/vehiculos/edit";
    }
    @PostMapping("/vehiculos/guardar")
    public String guardarOficina(@ModelAttribute("Vehiculo") Vehiculo vehiculo, RedirectAttributes redirectAttributes) {
        vehiculoRepository.save(vehiculo);
        redirectAttributes.addFlashAttribute("mensaje", "Vehiculo guardada con éxito");
        return "redirect:/administracion/vehiculos";
    }

    @GetMapping("/vehiculos/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Integer id, Model model) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de oficina inválido:" + id));
        model.addAttribute("vehiculo", vehiculo);
        return "administracion/vehiculos/edit";
    }

    @GetMapping("/vehiculos/borrar/{id}")
    public String borrarVehiculo(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de oficina inválido:" + id));
        vehiculoRepository.delete(vehiculo);
        redirectAttributes.addFlashAttribute("mensaje", "Oficina borrada con éxito");
        return "redirect:/administracion/vehiculos";
    }
}
