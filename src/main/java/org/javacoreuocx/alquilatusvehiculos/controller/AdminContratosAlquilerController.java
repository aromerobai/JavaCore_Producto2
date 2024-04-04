package org.javacoreuocx.alquilatusvehiculos.controller;

import org.javacoreuocx.alquilatusvehiculos.model.Cliente;
import org.javacoreuocx.alquilatusvehiculos.model.ContratoAlquiler;
import org.javacoreuocx.alquilatusvehiculos.model.Oficina;
import org.javacoreuocx.alquilatusvehiculos.model.Vehiculo;
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
    public String listarContratosAlquiler(Model model) {
        model.addAttribute("currentPage", "contratos");
        model.addAttribute("contratosAlquiler", contratoAlquilerRepository.findAll());
        return "administracion/contratos-alquiler";
    }

    @GetMapping("/contratos-alquiler/nuevo")
    public String mostrarFormularioDeNuevoContratoAlquiler(Model model) {
        List<Oficina> oficinas = oficinaRepository.findAll();
        List<Cliente> clientes = clienteRepository.findAll();
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();

        model.addAttribute("currentPage", "contrato");

        model.addAttribute("contratoAlquiler", new ContratoAlquiler());
        model.addAttribute("oficinas", oficinas);
        model.addAttribute("clientes", clientes);
        model.addAttribute("vehiculos", vehiculos);

        return "administracion/contratos-alquiler/edit";
    }

    @PostMapping("/contratos-alquiler/guardar")
    public String guardarContratoAlquiler(@ModelAttribute("contratoAlquiler") ContratoAlquiler contratoAlquiler, RedirectAttributes redirectAttributes) {
        System.out.println("====== SE ACCEDE AL CONTROLLER DE GUARDAR ========= \n\n");

        //Se limpian los contratosAlquilerVehiculos existentes (para evitar datos duplicados)
        contratoAlquiler.getContratoVehiculos().clear();

        contratoAlquilerRepository.save(contratoAlquiler);
        redirectAttributes.addFlashAttribute("mensaje", "Contrato guardado con éxito");
        return "redirect:/administracion/contratos-alquiler";
    }

    @GetMapping("/contratos-alquiler/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        ContratoAlquiler contratoAlquiler = contratoAlquilerRepository.findById(Long.valueOf(id)).orElseThrow(() -> new IllegalArgumentException("Id de la reserva inválido:" + id));
        List<Oficina> oficinas = oficinaRepository.findAll();
        List<Cliente> clientes = clienteRepository.findAll();
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();

        model.addAttribute("currentPage", "contrato");

        model.addAttribute("contratoAlquiler", contratoAlquiler);
        model.addAttribute("oficinas", oficinas);
        model.addAttribute("clientes", clientes);
        model.addAttribute("vehiculos", vehiculos);

        return "administracion/contratos-alquiler/edit";
    }
    @GetMapping("/contratos-alquiler/borrar/{id}")
    public String borrarContratoAlquiler(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ContratoAlquiler contratoAlquiler = contratoAlquilerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de oficina inválido:" + id));
        contratoAlquilerRepository.delete(contratoAlquiler);
        redirectAttributes.addFlashAttribute("mensaje", "Contrato borrado con éxito");
        return "redirect:/administracion/contratos-alquiler";
    }
}
