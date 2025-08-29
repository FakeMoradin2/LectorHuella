package com.chong.lectorDeHuella.controller;

import com.chong.lectorDeHuella.dto.OperadorDesdeHuellaDTO;
import com.chong.lectorDeHuella.model.Operador;
import com.chong.lectorDeHuella.repository.OperadorRepository;
import com.chong.lectorDeHuella.service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operadores")
public class OperadorController {

    @Autowired
    private final OperadorService operadorService;

    private final OperadorRepository operadorRepository;

    public OperadorController(OperadorService operadorService, OperadorRepository operadorRepository) {
        this.operadorService = operadorService;
        this.operadorRepository = operadorRepository;
    }

    // ========== LO QUE YA TENÍAS ==========
    @GetMapping
    public List<Operador> getAll() {
        return operadorService.findAll();
    }

    @PostMapping
    public Operador create(@RequestBody Operador operador) {
        return operadorService.createOperador(operador);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operador> getById(@PathVariable Integer id)
    {
        Optional<Operador> operador = Optional.ofNullable(operadorService.findById(id));
        return operador.isPresent() ? ResponseEntity.ok(operador.get()) : ResponseEntity.notFound().build();
    }

    // ========== NUEVOS PARA ESP32 ==========
    // Buscar por fingerId (para verificar si ya existe)
    @GetMapping("/by-finger/{fingerId}")
    public ResponseEntity<?> getByFinger(@PathVariable int fingerId) {
        return operadorService.findByFingerId(fingerId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("No existe operador con esa huella"));
    }

    // Registrar operador desde huella (enrolamiento sin GUI)
    @PostMapping("/registro-desde-huella")
    public ResponseEntity<?> registrarDesdeHuella(@RequestBody OperadorDesdeHuellaDTO dto) {
        if (operadorService.findByFingerId(dto.fingerId).isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un operador con ese fingerId");
        }
        Operador op = new Operador();
        op.setFingerId(dto.fingerId);
        op.setNombre(dto.nombre);
        op.setApellidoPaterno(dto.apellidoPaterno);
        op.setApellidoMaterno(dto.apellidoMaterno);
        op.setNumeroOperador(dto.numeroOperador);
        return ResponseEntity.ok(operadorService.save(op));
    }

    // ✅ Actualizar operador
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOperador(@PathVariable int id, @RequestBody Operador operador) {
        operador.setIdOperador(id);
        return operadorService.guardarCambioOperador(operador);
    }

    // ✅ Eliminar operador
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOperador(@PathVariable int id) {
        return operadorService.eliminarOperador(id);
    }
}
