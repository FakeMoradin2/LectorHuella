package com.chong.lectorDeHuella.service;

import com.chong.lectorDeHuella.model.Prestamo;
import com.chong.lectorDeHuella.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

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
