package org.javacoreuocx.alquilatusvehiculos.controller;

import org.javacoreuocx.alquilatusvehiculos.model.Cliente;
import org.javacoreuocx.alquilatusvehiculos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/administracion")
public class AdminClientesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("currentPage", "clientes");
        model.addAttribute("clientes", clienteRepository.findAll());
        return "administracion/clientes";
    }

    @GetMapping("/clientes/nuevo")
    public String mostrarFormularioDeNuevoCliente(Model model) {
        model.addAttribute("currentPage", "cliente");
        model.addAttribute("cliente", new Cliente());
        return "administracion/clientes/edit";
    }

    @PostMapping("/clientes/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes redirectAttributes) {
        clienteRepository.save(cliente);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente guardado con éxito");
        return "redirect:/administracion/clientes";
    }


    @GetMapping("/clientes/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de oficina inválido:" + id));
        model.addAttribute("currentPage", "cliente");
        model.addAttribute("cliente", cliente);
        return "administracion/clientes/edit";
    }

    @GetMapping("/clientes/borrar/{id}")
    public String borrarCliente(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de oficina inválido:" + id));
        clienteRepository.delete(cliente);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente borrado con éxito");
        return "redirect:/administracion/clientes";
    }
}
