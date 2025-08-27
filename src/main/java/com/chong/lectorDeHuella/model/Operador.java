package com.chong.lectorDeHuella.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "operador")
public class Operador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operador") // mapea al campo exacto de SQL
    private int idOperador;

    @Column(name = "finger_id", nullable = false, unique = true)
    private int fingerId;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido_paterno", length = 30)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 30)
    private String apellidoMaterno;

    @Column(name = "numero_operador", nullable = false, unique = true, length = 50)
    private String numeroOperador;

    // Relación 1:N con préstamo
    @OneToMany(mappedBy = "operador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private java.util.List<Prestamo> prestamos;

    // Getters y setters

    public int getIdOperador() {
        return idOperador;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public String getNumeroOperador() {
        return numeroOperador;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFingerId() {
        return fingerId;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public void setNumeroOperador(String numeroOperador) {
        this.numeroOperador = numeroOperador;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFingerId(int fingerId) {
        this.fingerId = fingerId;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
}
