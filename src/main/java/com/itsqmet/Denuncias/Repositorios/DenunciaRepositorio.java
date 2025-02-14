package com.itsqmet.Denuncias.Repositorios;

import com.itsqmet.Denuncias.Entidades.Denuncia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DenunciaRepositorio extends MongoRepository<Denuncia, String> {
    List<Denuncia> findByTituloContainingIgnoreCase(String titulo);

    long countByEstado(String estado);

    List<Denuncia> findByEstado(String estado);

    // Para el dashboard de autoridad
    long countByAutoridadIdAndEstado(String autoridadId, String estado);

    List<Denuncia> findByAutoridadId(String autoridadId);
}