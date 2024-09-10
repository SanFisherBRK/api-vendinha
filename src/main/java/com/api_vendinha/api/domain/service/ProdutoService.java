package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;

public interface ProdutoService {
    ProdutoResponseDto save(ProdutoRequestDto produtoRequestDto);
    ProdutoResponseDto update(ProdutoRequestDto produtoRequestDto, Long id);
}
