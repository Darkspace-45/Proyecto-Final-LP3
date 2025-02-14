package com.itsqmet.Denuncias.Repositorios;

import com.itsqmet.Denuncias.Entidades.ActividadReciente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActividadRecienteRepositorio extends MongoRepository<ActividadReciente, String> {
    List<ActividadReciente> findTop10ByOrderByFechaDesc();
}