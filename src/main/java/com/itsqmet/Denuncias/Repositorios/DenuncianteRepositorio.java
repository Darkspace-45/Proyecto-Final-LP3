package com.itsqmet.Denuncias.Repositorios;

import com.itsqmet.Denuncias.Entidades.Denunciante;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DenuncianteRepositorio extends MongoRepository<Denunciante, String> {
    List<Denunciante> findByNombreContainingIgnoreCase(String nombre);

    // Cambiamos el retorno a Optional<Denunciante>
    Optional<Denunciante> findByUsername(String username);
}