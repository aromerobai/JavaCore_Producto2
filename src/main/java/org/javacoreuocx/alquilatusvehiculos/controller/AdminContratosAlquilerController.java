package org.javacoreuocx.alquilatusvehiculos.controller;

import org.javacoreuocx.alquilatusvehiculos.model.*;
import org.javacoreuocx.alquilatusvehiculos.repository.ClienteRepository;
import org.javacoreuocx.alquilatusvehiculos.repository.ContratoAlquilerRepository;
import org.javacoreuocx.alquilatusvehiculos.repository.OficinaRepository;
import org.javacoreuocx.alquilatusvehiculos.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/administracion")
public class AdminContratosAlquilerController {

    @Autowired
    private ContratoAlquilerRepository contratoAlquilerRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;


    @GetMapping("/contratos-alquiler")
    public String listarOficinas(Model model) {

        System.out.println("LEGAMOS AQUÍ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        model.addAttribute("contratosAlquiler", contratoAlquilerRepository.findAll());

        return "administracion/contratos-alquiler";
    }

    @GetMapping("/contrato-alquiler/nuevo")
    public String mostrarFormularioDeNuevoContratoAlquiler(Model model) {
        model.addAttribute("contrato-alquiler", new Oficina());
        return "administracion/oficinas/edit";
    }

    @GetMapping("/contrato-alquiler/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Integer id, Model model) {
        ContratoAlquiler contratoAlquiler = contratoAlquilerRepository.findById(Long.valueOf(id)).orElseThrow(() -> new IllegalArgumentException("Id de la reserva inválido:" + id));
        List<Oficina> oficinas = oficinaRepository.findAll();
        List<Cliente> clientes = clienteRepository.findAll();
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();

        model.addAttribute("contratoAlquiler", contratoAlquiler);
        model.addAttribute("oficinas", oficinas);
        model.addAttribute("clientes", clientes);
        model.addAttribute("vehiculos", vehiculos);

        return "administracion/contratos-alquiler/edit";
    }

    @PostMapping("/contrato-alquiler/guardar")
    public String guardarOficina(@ModelAttribute("contratoAlquiler") ContratoAlquiler contratoAlquiler,  RedirectAttributes redirectAttributes) {

        System.out.println("ESTOS SON LOS NUEVO VEHICULOS \n\n");
        for (ContratoAlquilerVehiculo contratoVehiculo : contratoAlquiler.getContratoVehiculos()) {
            Vehiculo vehiculo = contratoVehiculo.getVehiculo();
            System.out.println("Vehicle Model: " + vehiculo.getModelo());
            System.out.println("Vehicle ID: " + vehiculo.getId());
        }

        contratoAlquilerRepository.save(contratoAlquiler);
        redirectAttributes.addFlashAttribute("mensaje", "Oficina guardada con éxito");
        return "redirect:/administracion/contratos-alquiler";
    }
}
