package com.chong.lectorDeHuella.service;

import com.chong.lectorDeHuella.model.Prestamo;
import com.chong.lectorDeHuella.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    public Prestamo createPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public ResponseEntity<?> guardarCambioPrestamo(Prestamo prestamo) {
        if (!prestamoRepository.existsById(prestamo.getIdPrestamo())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Préstamo no encontrado");
        }
        Prestamo actualizado = prestamoRepository.save(prestamo);
        return ResponseEntity.ok(actualizado);
    }

    public ResponseEntity<?> eliminarPrestamo(int id) {
        if (!prestamoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Préstamo no encontrado");
        }
        prestamoRepository.deleteById(id);
        return ResponseEntity.ok("Préstamo eliminado con éxito");
    }

    // ✅ Para GET /api/prestamos/{id}/operador
    public Optional<Prestamo> findById(int id) {
        return prestamoRepository.findById(id);
    }

    // ✅ Para GET /api/prestamos/por-operador/{idOperador}
    public List<Prestamo> findByOperador(int idOperador) {
        return prestamoRepository.findByOperador_IdOperador(idOperador);
    }

    // ✅ Útil en el endpoint crear-desde-huella
    public Prestamo save(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }
}
