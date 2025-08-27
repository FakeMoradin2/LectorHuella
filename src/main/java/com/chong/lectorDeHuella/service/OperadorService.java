package com.chong.lectorDeHuella.service;

import com.chong.lectorDeHuella.model.Operador;
import com.chong.lectorDeHuella.repository.OperadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperadorService {

    private final OperadorRepository operadorRepository;

    public OperadorService(OperadorRepository operadorRepository) {
        this.operadorRepository = operadorRepository;
    }

    public List<Operador> findAll() {
        return operadorRepository.findAll(); //get que tiene el repositorio
    }

    public Operador createOperador(Operador operador) {
        return operadorRepository.save(operador); //post del repositorio
    }

    // ✅ Para endpoints/ESP32
    public Optional<Operador> findByFingerId(int fingerId) {
        return operadorRepository.findByFingerId(fingerId);
    }

    // ✅ Útil para registrar “desde huella”
    public Operador save(Operador operador) {
        return operadorRepository.save(operador);
    }
}
