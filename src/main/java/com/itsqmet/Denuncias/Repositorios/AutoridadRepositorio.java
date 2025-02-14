package com.itsqmet.Denuncias.Repositorios;

import com.itsqmet.Denuncias.Entidades.Autoridad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AutoridadRepositorio extends MongoRepository<Autoridad, String> {
    List<Autoridad> findByNombreContainingIgnoreCase(String nombre);

}
