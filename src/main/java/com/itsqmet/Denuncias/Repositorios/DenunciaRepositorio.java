package com.itsqmet.Denuncias.Repositorios;

import com.itsqmet.Denuncias.Entidades.Denuncia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DenunciaRepositorio extends MongoRepository<Denuncia, String> {
    List<Denuncia> findByTituloContainingIgnoreCase(String titulo);
    List<Denuncia> findByDenuncianteId(String denuncianteId);
    long countByEstado(String estado);
    List<Denuncia> findByEstado(String estado);
    List<Denuncia> findByAutoridadId(String autoridadId);
}