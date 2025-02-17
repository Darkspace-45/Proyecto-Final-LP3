package com.itsqmet.Denuncias.Controladores;

import com.itsqmet.Denuncias.Servicios.DenunciaServicio;
import com.itsqmet.Denuncias.Servicios.DenuncianteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DenunciaServicio denunciaServicio;
    private final DenuncianteServicio denuncianteServicio;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("");

        switch (role) {
            case "ROLE_ADMIN":
                model.addAttribute("totalDenuncias", denunciaServicio.mostrarDenuncias().size());
                model.addAttribute("ultimasDenuncias", denunciaServicio.mostrarDenuncias());
                return "dashboard/admin";

            case "ROLE_DENUNCIANTE":
                String username = authentication.getName();
                // Obtener todas las denuncias del denunciante
                var denuncias = denunciaServicio.findByDenuncianteId(username);

                // Contar denuncias por estado
                long denunciasResueltas = denuncias.stream()
                        .filter(d -> "RESUELTO".equals(d.getEstado()))
                        .count();
                long denunciasProceso = denuncias.stream()
                        .filter(d -> "EN_PROCESO".equals(d.getEstado()))
                        .count();

                // Agregar todos los datos al modelo
                model.addAttribute("misDenuncias", denuncias);
                model.addAttribute("totalDenuncias", denuncias.size());
                model.addAttribute("denunciasResueltas", denunciasResueltas);
                model.addAttribute("denunciasProceso", denunciasProceso);

                return "dashboard/denunciante";

            case "ROLE_AUTORIDAD":
                String autoridadId = authentication.getName();
                model.addAttribute("denunciasPendientes",
                        denunciaServicio.contarDenunciasPorEstado("PENDIENTE"));
                model.addAttribute("denunciasEnProceso",
                        denunciaServicio.contarDenunciasPorEstado("EN_PROCESO"));
                model.addAttribute("denunciasResueltas",
                        denunciaServicio.contarDenunciasPorEstado("RESUELTO"));
                model.addAttribute("totalDenuncias",
                        denunciaServicio.contarDenunciasPorAutoridad(autoridadId));
                model.addAttribute("denunciasPendientesList",
                        denunciaServicio.obtenerDenunciasPendientes());
                return "dashboard/autoridad";

            default:
                return "redirect:/auth/login";
        }
    }
}
