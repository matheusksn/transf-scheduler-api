package com.matheusksn.transfschedulerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheusksn.transfschedulerapi.dto.TransferenciaRequestDTO;
import com.matheusksn.transfschedulerapi.exception.ErrorDetail;
import com.matheusksn.transfschedulerapi.model.Response;
import com.matheusksn.transfschedulerapi.model.Transferencia;
import com.matheusksn.transfschedulerapi.service.TransferenciaService;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping("/agendar")
    public ResponseEntity<Response> agendarTransferencia(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) throws Exception {
        try {
            Transferencia transf = transferenciaService.agendarTransferencia(transferenciaRequestDTO);
            return ResponseEntity.ok(new Response(200, "Transferência agendada com sucesso", transf));
        } catch (Exception e) {
            ErrorDetail errorDetail = new ErrorDetail(
                    "Erro ao agendar a transferência",
                    e.getMessage());
            return ResponseEntity.badRequest().body(new Response(400, "Erro na solicitação", errorDetail));
        }
    }




    @GetMapping("/consultar")
    public ResponseEntity<List<Transferencia>> consultarTransferencias() {
        List<Transferencia> transferencias = transferenciaService.consultarTransferencias();
        return new ResponseEntity<>(transferencias, HttpStatus.OK);
    }
}
