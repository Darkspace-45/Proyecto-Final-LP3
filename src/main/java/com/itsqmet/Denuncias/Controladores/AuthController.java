package com.itsqmet.Denuncias.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")  // Agregamos el método GET para mostrar la página
    public String showLoginForm() {
        return "auth/login";
    }

    @GetMapping("/register")  // Agregamos el método GET para mostrar el formulario
    public String showRegisterForm() {
        return "auth/register";
    }

    @PostMapping("/login")  // Este maneja el envío del formulario
    public String processLogin() {
        return "redirect:/dashboard";
    }

    @PostMapping("/register")  // Este maneja el registro
    public String processRegister() {
        return "redirect:/auth/login";
    }
}