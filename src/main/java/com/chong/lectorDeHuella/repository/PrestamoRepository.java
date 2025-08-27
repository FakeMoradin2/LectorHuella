package com.chong.lectorDeHuella.repository;

import com.chong.lectorDeHuella.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>
{
    List<Prestamo> findByOperador_IdOperador(int idOperador);
}

