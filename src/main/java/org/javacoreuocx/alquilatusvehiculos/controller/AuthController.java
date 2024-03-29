package org.javacoreuocx.alquilatusvehiculos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.javacoreuocx.alquilatusvehiculos.model.Cliente;
import org.javacoreuocx.alquilatusvehiculos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.javacoreuocx.alquilatusvehiculos.service.CustomSecurityService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private ClienteService clienteService;

    private CustomSecurityService customSecurityService;

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("user_name") String user_name, @RequestParam("password") String password) {
        customSecurityService.autoLogin(user_name, password);
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String mostrarRegister(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "register"; // Asegúrate de tener la plantilla Thymeleaf 'registro.html'
    }

    @PostMapping("/register")
    public String registrarCliente(Cliente cliente) {
        clienteService.registrarCliente(cliente);
        customSecurityService.autoLogin(cliente.getEmail(), cliente.getPassword());
        return "redirect:/home";
    }
}
