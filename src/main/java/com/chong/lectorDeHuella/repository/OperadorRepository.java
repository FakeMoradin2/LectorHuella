package com.chong.lectorDeHuella.repository;
import com.chong.lectorDeHuella.model.Operador;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperadorRepository extends JpaRepository<Operador, Integer>
{
    Optional<Operador> findByFingerId(int fingerId);
}
