package org.javacoreuocx.alquilatusvehiculos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.javacoreuocx.alquilatusvehiculos.repository.VehiculoRepository;

@Controller
public class ClienteController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping("/home")
    public String mostrarClienteHome(Model model) {
        model.addAttribute("currentPage", "home");
        return "cliente/home";
    }

    @GetMapping("/coches")
    public String mostrarClienteCoches(Model model) {
        model.addAttribute("currentPage", "coches");
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "cliente/coches";
    }

    @GetMapping("/reservas")
    public String mostrarClienteReservas(Model model) {
        model.addAttribute("currentPage", "reservas");
        return "cliente/reservas";
    }

}
