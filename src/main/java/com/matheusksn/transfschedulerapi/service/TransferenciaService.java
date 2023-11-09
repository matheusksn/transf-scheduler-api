package com.matheusksn.transfschedulerapi.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusksn.transfschedulerapi.dto.TransferenciaRequestDTO;
import com.matheusksn.transfschedulerapi.exception.DataTransferenciaInvalidaException;
import com.matheusksn.transfschedulerapi.exception.ValorTransferenciaInvalidoException;
import com.matheusksn.transfschedulerapi.model.Transferencia;
import com.matheusksn.transfschedulerapi.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

    @Autowired
    TransferenciaRepository transferenciaRepository;

    public Transferencia agendarTransferencia(TransferenciaRequestDTO transferenciaRequestDTO)
            throws DataTransferenciaInvalidaException, ValorTransferenciaInvalidoException {
        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem(transferenciaRequestDTO.getContaOrigem());
        transferencia.setContaDestino(transferenciaRequestDTO.getContaDestino());
        transferencia.setValor(transferenciaRequestDTO.getValor());
        transferencia.setDataTransferencia(transferenciaRequestDTO.getDataTransferencia());
        transferencia.setStatus("Agendada");
        transferencia.setDataAgendamento(LocalDate.now());

        validarTransferencia(transferencia);

        transferencia.setTaxa(calcularTaxa(transferencia.getDataTransferencia()));
        transferenciaRepository.save(transferencia);

        return transferencia;
    }

    private void validarTransferencia(Transferencia transferencia)
            throws DataTransferenciaInvalidaException, ValorTransferenciaInvalidoException {
        if (transferencia.getDataTransferencia().isBefore(LocalDate.now())) {
            throw new DataTransferenciaInvalidaException("Data da transferência deve ser posterior à data atual");
        }

        if (transferencia.getValor() <= 0) {
            throw new ValorTransferenciaInvalidoException("Valor da transferência deve ser maior que zero");
        }
    }

    private Double calcularTaxa(LocalDate dataTransferencia) throws DataTransferenciaInvalidaException {
        int diasParaTransferencia = (int) ChronoUnit.DAYS.between(LocalDate.now(), dataTransferencia);
        double taxa = 0.0;

        if (diasParaTransferencia == 0) {
            taxa = 0.025;
        } else if (diasParaTransferencia >= 1 && diasParaTransferencia <= 10) {
            taxa = 0.0;
        } else if (diasParaTransferencia >= 11 && diasParaTransferencia <= 20) {
            taxa = 0.082;
        } else if (diasParaTransferencia >= 21 && diasParaTransferencia <= 30) {
            taxa = 0.069;
        } else if (diasParaTransferencia >= 31 && diasParaTransferencia <= 40) {
            taxa = 0.047;
        } else if (diasParaTransferencia >= 41 && diasParaTransferencia <= 50) {
            taxa = 0.017;
        } else {
            throw new DataTransferenciaInvalidaException("Taxa não aplicável");
        }

        return taxa;
    }

    public List<Transferencia> consultarTransferencias() {
        return transferenciaRepository.findAll();
    }
}
