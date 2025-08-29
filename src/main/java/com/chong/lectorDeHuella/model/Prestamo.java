package com.chong.lectorDeHuella.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private Integer idPrestamo;

    @Column(name = "concepto", nullable = false, length = 100)
    private String concepto;

    @Column(name = "monto", nullable = false)
    private double monto;

    @Column(name = "aprobado", nullable = false)
    private boolean aprobado;

    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDateTime fechaPrestamo;

    // Relación muchos a uno con Operador
    @ManyToOne
    @JoinColumn(name = "id_operador", nullable = false)
    @JsonBackReference
    private Operador operador;

    // Getters y setters


    public int getIdPrestamo() {
        return idPrestamo;
    }

    public Operador getOperador() {
        return operador;
    }

    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public double getMonto() {
        return monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
