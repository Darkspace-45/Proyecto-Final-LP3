package com.itsqmet.Denuncias.Servicios;


import com.itsqmet.Denuncias.Entidades.ActividadReciente;
import com.itsqmet.Denuncias.Entidades.Denuncia;
import com.itsqmet.Denuncias.Repositorios.ActividadRecienteRepositorio;
import com.itsqmet.Denuncias.Repositorios.DenunciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DenunciaServicio {

    @Autowired
    private DenunciaRepositorio denunciaRepositorio;

    @Autowired
    private ActividadRecienteRepositorio actividadRecienteRepositorio;

    // Método para mostrar todas las denuncias
    public List<Denuncia> mostrarDenuncias() {
        return denunciaRepositorio.findAll();
    }

    // Método para buscar denuncias por título
    public List<Denuncia> buscarDenunciaPorTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            return denunciaRepositorio.findAll();
        } else {
            return denunciaRepositorio.findByTituloContainingIgnoreCase(titulo);
        }
    }

    // Método para guardar una nueva denuncia
    public void guardarDenuncia(Denuncia denuncia) {
        denunciaRepositorio.save(denuncia);
    }

    // Método para eliminar una denuncia por ID
    public void eliminarDenuncia(String id) {
        denunciaRepositorio.deleteById(id);
    }

    // Método para buscar una denuncia por ID
    public Optional<Denuncia> buscarDenunciaPorId(String id) {
        return denunciaRepositorio.findById(id);
    }

    public long contarDenunciasPorEstado(String estado) {
        return denunciaRepositorio.countByEstado(estado);
    }

    public long contarDenunciasPorAutoridad(String autoridadId) {
        return denunciaRepositorio.findByAutoridadId(autoridadId).size();
    }

    public List<Denuncia> obtenerDenunciasPendientes() {
        return denunciaRepositorio.findByEstado("PENDIENTE");
    }

    public List<ActividadReciente> obtenerActividadReciente() {
        return actividadRecienteRepositorio.findTop10ByOrderByFechaDesc();
    }

    // Método helper para registrar actividad
    public void registrarActividad(String descripcion, String tipo, String denunciaId, String usuarioId) {
        ActividadReciente actividad = new ActividadReciente(descripcion, tipo, denunciaId, usuarioId);
        actividadRecienteRepositorio.save(actividad);
    }


}
