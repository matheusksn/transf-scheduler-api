package com.matheusksn.transfschedulerapi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Transferencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contaOrigem;
    private String contaDestino;
    private Double valor;
    private Double taxa;
    private LocalDate dataTransferencia;
    private LocalDate dataAgendamento;
   
    public Transferencia(String contaOrigem, String contaDestino, Double valor, Double taxa, LocalDate dataTransferencia, LocalDate dataAgendamento) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.taxa = taxa;
        this.dataTransferencia = dataTransferencia;
        this.dataAgendamento = dataAgendamento;
    }

    public Double getValorComTaxa() {
        return valor + (valor * taxa);
    }
}
