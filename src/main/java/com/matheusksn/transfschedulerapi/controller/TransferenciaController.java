package com.matheusksn.transfschedulerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheusksn.transfschedulerapi.dto.TransferenciaRequestDTO;
import com.matheusksn.transfschedulerapi.exception.DataTransferenciaInvalidaException;
import com.matheusksn.transfschedulerapi.exception.ErrorDetail;
import com.matheusksn.transfschedulerapi.exception.ValorTransferenciaInvalidoException;
import com.matheusksn.transfschedulerapi.model.Response;
import com.matheusksn.transfschedulerapi.model.Transferencia;
import com.matheusksn.transfschedulerapi.service.TransferenciaService;

@RestController
@RequestMapping("/api/transferencias")
@CrossOrigin(origins = "http://localhost:4200")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping("/agendar")
    public ResponseEntity<Response> agendarTransferencia(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) {
        try {
            Transferencia transf = transferenciaService.agendarTransferencia(transferenciaRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response(201, "Transferência agendada com sucesso", transf));
        } catch (DataTransferenciaInvalidaException | ValorTransferenciaInvalidoException e) {
            ErrorDetail errorDetail = new ErrorDetail("Erro ao agendar a transferência", e.getMessage());
            return ResponseEntity.badRequest().body(new Response(400, "Erro na solicitação", errorDetail));
        } catch (Exception e) {
            ErrorDetail errorDetail = new ErrorDetail(
                    "Erro ao agendar a transferência",
                    "Erro interno no servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "Erro interno no servidor", errorDetail));
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<Response> consultarTransferencias() {
        try {
            List<Transferencia> transferencias = transferenciaService.consultarTransferencias();
            return ResponseEntity.ok(new Response(200, "Consulta de transferências realizada com sucesso", transferencias));
        } catch (Exception e) {
            ErrorDetail errorDetail = new ErrorDetail(
                    "Erro ao consultar transferências",
                    "Erro interno no servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, "Erro interno no servidor", errorDetail));
        }
    }
}
