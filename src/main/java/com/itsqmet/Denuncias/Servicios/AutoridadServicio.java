package com.itsqmet.Denuncias.Servicios;


import com.itsqmet.Denuncias.Entidades.Autoridad;
import com.itsqmet.Denuncias.Repositorios.AutoridadRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoridadServicio {

    @Autowired
    private AutoridadRepositorio autoridadRepositorio;

    // Método para mostrar todas las autoridades
    public List<Autoridad> mostrarAutoridades() {
        return autoridadRepositorio.findAll();
    }

    // Método para buscar autoridades por nombre
    public List<Autoridad> mostrarAutoridadPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return autoridadRepositorio.findAll();
        } else {
            return autoridadRepositorio.findByNombreContainingIgnoreCase(nombre);
        }
    }

    // Método para guardar una nueva Autoridad
    public void guardarAutoridad(Autoridad autoridad) {
        autoridadRepositorio.save(autoridad);
    }

    // Método para eliminar una Autoridad según el ID
    public void eliminarAutoridad(String id) {
        autoridadRepositorio.deleteById(id);
    }

    // Método para buscar una Autoridad por ID
    public Optional<Autoridad> buscarAutoridadPorID(String id) {
        return autoridadRepositorio.findById(id);
    }
}
