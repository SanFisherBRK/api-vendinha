package com.api_vendinha.api.domain.dtos.request;

import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class VendaRequestDto {
    private Long id;
    private Integer quantidade;
    private BigDecimal price;
    private User user;
    private Produto produto;
}
