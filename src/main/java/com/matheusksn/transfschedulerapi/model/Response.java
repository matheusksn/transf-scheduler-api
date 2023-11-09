package com.matheusksn.transfschedulerapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Response{

	private int codigo;
    private String mensagem;
    private Object conteudo;
}
