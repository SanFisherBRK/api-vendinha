package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.request.VendaRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.dtos.response.VendaResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.Venda;

import java.util.List;

public interface VendaService {
    VendaResponseDto save(VendaRequestDto vendaRequestDto);
    VendaResponseDto update(VendaRequestDto vendaRequestDto, Long id);
    List<Venda> findAll();
    Venda findById(Long id);
    void deletar(Long id);
}
