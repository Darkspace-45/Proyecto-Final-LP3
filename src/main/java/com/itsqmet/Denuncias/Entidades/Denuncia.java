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
@Document(collection = "denuncias")
public class Denuncia {
    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private String ubicacion;
    private String categoria;
    private String file;
    private LocalDateTime fecha;
    private String estado; // "PENDIENTE", "EN_PROCESO", "RESUELTO"
    private String autoridadId; // ID de la autoridad asignada
    private String denuncianteId; // ID del denunciante que creó la denuncia


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDenuncianteId() {
        return denuncianteId;
    }

    public void setDenuncianteId(String denuncianteId) {
        this.denuncianteId = denuncianteId;
    }

    public String getAutoridadId() {
        return autoridadId;
    }

    public void setAutoridadId(String autoridadId) {
        this.autoridadId = autoridadId;
    }
}
