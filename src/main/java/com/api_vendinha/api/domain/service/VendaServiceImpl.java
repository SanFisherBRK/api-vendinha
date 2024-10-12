package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.Infrastructure.repository.VendaRepository;
import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.request.VendaRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.dtos.response.VendaResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import com.api_vendinha.api.domain.entities.Venda;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VendaServiceImpl implements VendaService{
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    public VendaServiceImpl(VendaRepository vendaRepository, ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public VendaResponseDto save(VendaRequestDto vendaRequestDto) {
        // Recupera o produto associado à venda
        Produto produto = produtoRepository.findById(vendaRequestDto.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Cria uma nova instância de Venda
        Venda venda = new Venda();

        venda.setId(vendaRequestDto.getId());
        venda.setQuantidade(vendaRequestDto.getQuantidade());
        venda.setPrice(vendaRequestDto.getPrice());
        venda.setUser(vendaRequestDto.getUser());
        venda.setProduto(vendaRequestDto.getProduto());

        // Verifica se a quantidade não é nula
        if (venda.getQuantidade() == null) {
            throw new RuntimeException("Quantidade não pode ser nula");
        }

        // Verifica se há quantidade suficiente
        if (produto.getQuantidade() < venda.getQuantidade()) {
            throw new RuntimeException("Quantidade insuficiente em estoque");
        }

        venda.setPrice(BigDecimal.valueOf(produto.getPreco() * venda.getQuantidade()));

        // Salva a venda no repositório
        Venda saveVenda = vendaRepository.save(venda);

        // Cria e preenche o VendaResponseDto com os dados da venda salva
        VendaResponseDto vendaRes = new VendaResponseDto();
        vendaRes.setId(saveVenda.getId());
        vendaRes.setQuantidade(saveVenda.getQuantidade());
        vendaRes.setPrice(saveVenda.getPrice());
        vendaRes.setUser(saveVenda.getUser());
        vendaRes.setProduto(saveVenda.getProduto());



        produto.setQuantidade(produto.getQuantidade() - venda.getQuantidade());

        produtoRepository.save(produto);

        return vendaRes;
    }


    @Override
    public VendaResponseDto update(VendaRequestDto vendaRequestDto, Long id) {
        return null;
    }

    @Override
    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    @Override
    public Venda findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O id fornecido não pode ser nulo");
        }
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada para o ID: " + id));
    }


    @Override
    public void deletar(Long id) {
        //Não deletar vendas manter m registro
    }
}
