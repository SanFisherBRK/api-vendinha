package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.service.ProdutoService;
import com.api_vendinha.api.domain.service.UserServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtorService;
    public ProdutoController(ProdutoService produtorService){
        this.produtorService = produtorService;
    }

    @PostMapping("/save")
    ProdutoResponseDto save(@RequestBody ProdutoRequestDto produtoRequestDto){
        return produtorService.save(produtoRequestDto);
    }

    @PostMapping("/update/{id}")
    ProdutoResponseDto update(@RequestBody ProdutoRequestDto produtoRequestDto, @PathVariable Long id){
        return produtorService.update(produtoRequestDto, id);
    }

}
