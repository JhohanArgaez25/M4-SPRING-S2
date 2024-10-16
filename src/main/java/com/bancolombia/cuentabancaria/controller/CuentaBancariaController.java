package com.bancolombia.cuentabancaria.controller;

import com.bancolombia.cuentabancaria.model.CuentaBancariaEntity;
import com.bancolombia.cuentabancaria.service.CuentaBancariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/cuenta")
public class CuentaBancariaController {

    private final CuentaBancariaService cuentaBancariaService;

    public CuentaBancariaController(CuentaBancariaService cuentaBancariaService) {
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @GetMapping(path = "/saldo")
    public ResponseEntity<Object> saldo(@RequestParam String cuenta){
        Map<String, Object> message = new HashMap<>();
        message.put("saldo", cuentaBancariaService.getCuenta(cuenta).getSaldo());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/deposito")
    public ResponseEntity<Object> deposito(@RequestParam BigDecimal valor, @RequestParam String cuenta){
        Map<String, Object> message = new HashMap<>();
        CuentaBancariaEntity cuentaEntity = cuentaBancariaService.deposito(valor, cuenta);
        message.put("message", "Deposito exitoso");
        message.put("saldo", cuentaEntity.getSaldo());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/retiro")
    public ResponseEntity<Object> retiro(@RequestParam BigDecimal valor, @RequestParam String cuenta){
        Map<String, Object> message = new HashMap<>();
        CuentaBancariaEntity cuentaEntity = cuentaBancariaService.retiro(valor, cuenta);
        message.put("message", "Retiro exitoso");
        message.put("saldo", cuentaEntity.getSaldo());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
