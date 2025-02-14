package com.itsqmet.Denuncias.Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "actividad_reciente")
public class ActividadReciente {
    @Id
    private String id;
    private String descripcion;
    private LocalDateTime fecha;
    private String tipo; // Por ejemplo: "ACTUALIZACION", "NUEVA_DENUNCIA", "RESOLUCION"
    private String denunciaId; // Referencia a la denuncia relacionada
    private String usuarioId; // ID del usuario que realizó la acción

    // Constructor conveniente para crear actividades
    public ActividadReciente(String descripcion, String tipo, String denunciaId, String usuarioId) {
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
        this.tipo = tipo;
        this.denunciaId = denunciaId;
        this.usuarioId = usuarioId;
    }
}