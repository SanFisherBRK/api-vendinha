package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.VendaRequestDto;
import com.api_vendinha.api.domain.dtos.response.VendaResponseDto;
import com.api_vendinha.api.domain.entities.Venda;
import com.api_vendinha.api.domain.service.ProdutoService;
import com.api_vendinha.api.domain.service.VendaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {
    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping("/venda")
    public VendaResponseDto createVenda(@RequestBody VendaRequestDto vendaRequestDto) {
        return vendaService.save(vendaRequestDto);
    }

    @GetMapping("/findAll")
    public List<Venda> getAllVendas() {
        return vendaService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Venda findById(@PathVariable Long id) {
        return vendaService.findById(id);
    }
}