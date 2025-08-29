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

    private final PrestamoRepository prestamoRepository;

    @Autowired
    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    public Prestamo createPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    // 🔹 Actualizar con merge para no perder operador
    public ResponseEntity<?> guardarCambioPrestamo(Prestamo body) {
        Optional<Prestamo> opt = prestamoRepository.findById(body.getIdPrestamo());
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Préstamo no encontrado");
        }

        Prestamo existing = opt.get();

        // Copiar solo campos editables
        existing.setConcepto(body.getConcepto());
        existing.setMonto(body.getMonto());
        existing.setAprobado(body.isAprobado());
        existing.setFechaPrestamo(body.getFechaPrestamo());
        // ⚠️ No tocar existing.setOperador()

        Prestamo actualizado = prestamoRepository.save(existing);
        return ResponseEntity.ok(actualizado);
    }

    public ResponseEntity<?> eliminarPrestamo(int id) {
        if (!prestamoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Préstamo no encontrado");
        }
        prestamoRepository.deleteById(id);
        return ResponseEntity.ok("Préstamo eliminado con éxito");
    }

    public Optional<Prestamo> findById(int id) {
        return prestamoRepository.findById(id);
    }

    public List<Prestamo> findByOperador(int idOperador) {
        return prestamoRepository.findByOperador_IdOperador(idOperador);
    }

    public Prestamo save(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }
}
