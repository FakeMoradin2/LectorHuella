package com.chong.lectorDeHuella.service;

import com.chong.lectorDeHuella.model.Operador;
import com.chong.lectorDeHuella.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperadorService {

    @Autowired
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

    public ResponseEntity<?> guardarCambioOperador(Operador operador) {
        if (!operadorRepository.existsById(operador.getIdOperador())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Operador no encontrado");
        }
        Operador actualizado = operadorRepository.save(operador);
        return ResponseEntity.ok(actualizado);
    }

    public ResponseEntity<?> eliminarOperador(int id) {
        if (!operadorRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Operador no encontrado");
        }
        operadorRepository.deleteById(id);
        return ResponseEntity.ok("Operador eliminado con éxito");
    }
}
