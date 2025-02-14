package com.itsqmet.Denuncias.Controladores;

import com.itsqmet.Denuncias.Servicios.DenunciaServicio;
import com.itsqmet.Denuncias.Servicios.DenuncianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private DenunciaServicio denunciaServicio;

    @Autowired
    private DenuncianteServicio denuncianteServicio;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "dashboard/admin";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DENUNCIANTE"))) {
            return "dashboard/denunciante";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_AUTORIDAD"))) {
            String autoridadId = authentication.getName();

            model.addAttribute("denunciasPendientes",
                    denunciaServicio.contarDenunciasPorEstado("PENDIENTE"));
            model.addAttribute("denunciasEnProceso",
                    denunciaServicio.contarDenunciasPorEstado("EN_PROCESO"));
            model.addAttribute("denunciasResueltas",
                    denunciaServicio.contarDenunciasPorEstado("RESUELTO"));
            model.addAttribute("totalDenuncias",
                    denunciaServicio.contarDenunciasPorAutoridad(autoridadId));

            // Lista de denuncias pendientes
            model.addAttribute("denunciasPendientesList",
                    denunciaServicio.obtenerDenunciasPendientes());

            return "dashboard/autoridad";
        }

        return "redirect:/login";
    }

    @GetMapping("/dashboard/autoridad")
    public String autoridadDashboard(Model model, Authentication authentication) {
        // Obtener el ID de la autoridad actual
        String autoridadId = authentication.getName();

        // Agregar datos al modelo
        model.addAttribute("denunciasPendientes", denunciaServicio.contarDenunciasPorEstado("PENDIENTE"));
        model.addAttribute("denunciasEnProceso", denunciaServicio.contarDenunciasPorEstado("EN_PROCESO"));
        model.addAttribute("denunciasResueltas", denunciaServicio.contarDenunciasPorEstado("RESUELTO"));
        model.addAttribute("totalDenuncias", denunciaServicio.contarDenunciasPorAutoridad(autoridadId));

        // Lista de denuncias pendientes
        model.addAttribute("denunciasPendientesList", denunciaServicio.obtenerDenunciasPendientes());

        // Actividad reciente
        model.addAttribute("actividadReciente", denunciaServicio.obtenerActividadReciente());

        return "dashboard/autoridad";
    }

}
