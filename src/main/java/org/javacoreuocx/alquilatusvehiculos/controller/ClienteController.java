package org.javacoreuocx.alquilatusvehiculos.controller;

import org.javacoreuocx.alquilatusvehiculos.model.Cliente;
import org.javacoreuocx.alquilatusvehiculos.model.ContratoAlquiler;
import org.javacoreuocx.alquilatusvehiculos.model.Oficina;
import org.javacoreuocx.alquilatusvehiculos.model.Vehiculo;
import org.javacoreuocx.alquilatusvehiculos.repository.ClienteRepository;
import org.javacoreuocx.alquilatusvehiculos.repository.ContratoAlquilerRepository;
import org.javacoreuocx.alquilatusvehiculos.repository.OficinaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.javacoreuocx.alquilatusvehiculos.repository.VehiculoRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContratoAlquilerRepository contratoAlquilerRepository;
    @Autowired
    private OficinaRepository oficinaRepository;

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

    @GetMapping("/reservas/nueva")
    public String mostrarFormularioDeNuevoContratoAlquiler(Model model) {
        List<Oficina> oficinas = oficinaRepository.findAll();
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();

        model.addAttribute("contratoAlquiler", new ContratoAlquiler());
        model.addAttribute("oficinas", oficinas);
        model.addAttribute("vehiculos", vehiculos);

        return "cliente/reservas/nueva";
    }

    @PostMapping("/reservas/guardar")
    public String guardarContratoAlquiler(@ModelAttribute("contratoAlquiler") ContratoAlquiler contratoAlquiler, RedirectAttributes redirectAttributes) {

        //Se limpian los contratosAlquilerVehiculos existentes (para evitar datos duplicados)
        contratoAlquiler.getContratoVehiculos().clear();

         /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        */

        // Las peticiones a DB de usuario están hardcodeadas. Deberán cambiarse al implementar la funcionalidad de login.
        String email = "maria.lopez@empresa.com";
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);

        if(cliente.isPresent()) {
            contratoAlquiler.setCliente(cliente.get());
            contratoAlquilerRepository.save(contratoAlquiler);
            redirectAttributes.addFlashAttribute("mensaje", "Reserva realizada con éxito");
            return "redirect:/reservas";
        } else {
            return "cliente/home";
        }
    }

    @GetMapping("/contacto")
    public String mostrarClienteContacto(Model model) {
        model.addAttribute("currentPage", "contacto");
        return "cliente/contacto";
    }


}
