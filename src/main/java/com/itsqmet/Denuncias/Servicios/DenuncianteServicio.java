package com.itsqmet.Denuncias.Servicios;


import com.itsqmet.Denuncias.Entidades.Denunciante;
import com.itsqmet.Denuncias.Repositorios.DenuncianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DenuncianteServicio {

    @Autowired
    private DenuncianteRepositorio denuncianteRepositorio;

    // Método para mostrar todos los denunciantes
    public List<Denunciante> mostrarDenunciantes() {
        return denuncianteRepositorio.findAll();
    }

    // Método para buscar denunciantes por nombre
    public List<Denunciante> mostrarDenunciantePorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return denuncianteRepositorio.findAll();
        } else {
            return denuncianteRepositorio.findByNombreContainingIgnoreCase(nombre);
        }
    }

    // Método para guardar un nuevo Denunciante
    public void guardarDenunciante(Denunciante denunciante) {
        denuncianteRepositorio.save(denunciante);
    }

    // Método para eliminar un Denunciante según el ID
    public void eliminarDenunciante(String id) {
        denuncianteRepositorio.deleteById(id);
    }

    // Método para buscar un Denunciante por ID
    public Optional<Denunciante> buscarDenunciantePorID(String id) {
        return denuncianteRepositorio.findById(id);
    }
}

