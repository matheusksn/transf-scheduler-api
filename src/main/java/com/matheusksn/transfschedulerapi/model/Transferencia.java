	package com.matheusksn.transfschedulerapi.model;
	
	import java.time.LocalDate;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;
	
	@Entity
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
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
	    private String status;
	     
	    public Double getValorComTaxa() {
	        return valor + (valor * taxa);
	    }
	
	    public void setContaOrigem(String contaOrigem) {
	        if (contaOrigem != null && contaOrigem.matches("\\d{10}")) {
	            this.contaOrigem = contaOrigem;
	        } else {
	            throw new IllegalArgumentException("A conta de origem deve ter exatamente 10 dígitos numéricos.");
	        }
	    }
	
	    public void setContaDestino(String contaDestino) {
	        if (contaDestino != null && contaDestino.matches("\\d{10}")) {
	            this.contaDestino = contaDestino;
	        } else {
	            throw new IllegalArgumentException("A conta de destino deve ter exatamente 10 dígitos numéricos.");
	        }
	    }
	}
