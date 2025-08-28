package com.chong.lectorDeHuella.controller;

import com.chong.lectorDeHuella.dto.PrestamoDesdeHuellaDTO;
import com.chong.lectorDeHuella.model.Operador;
import com.chong.lectorDeHuella.model.Prestamo;
import com.chong.lectorDeHuella.service.OperadorService;
import com.chong.lectorDeHuella.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private final PrestamoService prestamoService;
    private final OperadorService operadorService;

    public PrestamoController(PrestamoService prestamoService, OperadorService operadorService) {
        this.prestamoService = prestamoService;
        this.operadorService = operadorService;
    }

    // ========== LO QUE YA TENÍAS ==========
    @GetMapping
    public List<Prestamo> getAllPrestamos() {
        return prestamoService.findAll();
    }

    @PostMapping
    public Prestamo createPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.createPrestamo(prestamo);
    }

    @GetMapping("/{id}/operador")
    public ResponseEntity<?> getOperadorByPrestamo(@PathVariable int id) {
        return prestamoService.findById(id)
                .map(prestamo -> ResponseEntity.ok(prestamo.getOperador()))
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== NUEVOS PARA ESP32 ==========
    // Crear préstamo desde huella (usa fingerId + concepto + monto)
    @PostMapping("/crear-desde-huella")
    public ResponseEntity<?> crearDesdeHuella(@RequestBody PrestamoDesdeHuellaDTO dto) {
        var opt = operadorService.findByFingerId(dto.fingerId);
        if (opt.isEmpty()) return ResponseEntity.status(404).body("Operador no encontrado para esa huella");

        Operador operador = opt.get();
        Prestamo p = new Prestamo();
        p.setConcepto(dto.concepto);
        p.setMonto(dto.monto);
        p.setAprobado(dto.aprobado != null ? dto.aprobado : false);
        p.setFechaPrestamo(
                (dto.fechaPrestamo != null && !dto.fechaPrestamo.isBlank())
                        ? LocalDateTime.parse(dto.fechaPrestamo)  // "2025-08-25T10:15:00"
                        : LocalDateTime.now()
        );
        p.setOperador(operador);

        return ResponseEntity.ok(prestamoService.save(p));
    }

    // (Opcional) Listar préstamos de un operador
    @GetMapping("/por-operador/{idOperador}")
    public List<Prestamo> getByOperador(@PathVariable int idOperador) {
        return prestamoService.findByOperador(idOperador);
    }

    // ✅ Actualizar préstamo
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePrestamo(@PathVariable int id, @RequestBody Prestamo prestamo) {
        prestamo.setIdPrestamo(id);
        return prestamoService.guardarCambioPrestamo(prestamo);
    }

    // ✅ Eliminar préstamo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrestamo(@PathVariable int id) {
        return prestamoService.eliminarPrestamo(id);
    }
}
