package org.javacoreuocx.alquilatusvehiculos.controller;


import org.javacoreuocx.alquilatusvehiculos.model.Cliente;
import org.javacoreuocx.alquilatusvehiculos.model.ContratoAlquiler;
import org.javacoreuocx.alquilatusvehiculos.repository.ClienteRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.javacoreuocx.alquilatusvehiculos.repository.VehiculoRepository;
import org.javacoreuocx.alquilatusvehiculos.repository.ContratoAlquilerRepository;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ContratoAlquilerRepository contratoAlquilerRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/home")
    public String mostrarClienteHome(Model model) {
        return "cliente/home";
    }

    @GetMapping("/coches")
    public String mostrarClienteCoches(Model model) {
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "cliente/coches";
    }

    @GetMapping("/reservas")
    public String mostrarClienteReservas(Model model) {
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        */

        // Las peticiones a DB de usuario están hardcodeadas. Deberán cambiarse al implementar la funcionalidad de login.
        String email = "maria.lopez@empresa.com";
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);

        if (cliente.isPresent()) {
            List<ContratoAlquiler> contratosAlquiler = contratoAlquilerRepository.findByClienteId(cliente.get().getId());
            model.addAttribute("contratosAlquiler", contratosAlquiler);
            return "cliente/reservas";
        } else {
            return "cliente/home";
        }
    }

}
