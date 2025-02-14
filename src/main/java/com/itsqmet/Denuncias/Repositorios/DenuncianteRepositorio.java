package com.itsqmet.Denuncias.Repositorios;

import com.itsqmet.Denuncias.Entidades.Denunciante;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DenuncianteRepositorio extends MongoRepository<Denunciante, String> {
    List<Denunciante> findByNombreContainingIgnoreCase(String nombre);

    Denunciante findByUsername(String username);
}
