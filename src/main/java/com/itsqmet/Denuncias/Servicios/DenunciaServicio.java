package com.itsqmet.Denuncias.Servicios;

import com.itsqmet.Denuncias.Entidades.Denuncia;
import com.itsqmet.Denuncias.Repositorios.DenunciaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DenunciaServicio {

    private final DenunciaRepositorio denunciaRepositorio;

    // Métodos existentes
    public List<Denuncia> mostrarDenuncias() {
        return denunciaRepositorio.findAll();
    }

    public void guardarDenuncia(Denuncia denuncia) {
        denunciaRepositorio.save(denuncia);
    }

    public void eliminarDenuncia(String id) {
        denunciaRepositorio.deleteById(id);
    }

    public Optional<Denuncia> buscarDenunciaPorId(String id) {
        return denunciaRepositorio.findById(id);
    }

    // Nuevos métodos
    public List<Denuncia> findByDenuncianteId(String denuncianteId) {
        return denunciaRepositorio.findByDenuncianteId(denuncianteId);
    }

    public long contarDenunciasPorEstado(String estado) {
        return denunciaRepositorio.countByEstado(estado);
    }

    public List<Denuncia> obtenerDenunciasPendientes() {
        return denunciaRepositorio.findByEstado("PENDIENTE");
    }

    public long contarDenunciasPorAutoridad(String autoridadId) {
        return denunciaRepositorio.findByAutoridadId(autoridadId).size();
    }
}