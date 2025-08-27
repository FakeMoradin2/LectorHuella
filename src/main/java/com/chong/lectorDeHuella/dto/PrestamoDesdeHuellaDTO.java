package com.chong.lectorDeHuella.dto;

public class PrestamoDesdeHuellaDTO
{
    public int fingerId;
    public String concepto;
    public double monto;
    public Boolean aprobado;     // opcional
    public String fechaPrestamo; // ISO-8601 opcional: "2025-08-25T10:15:00"
}
