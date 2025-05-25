package com.seuprojeto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMax(value = "10000.00", message = "Preço máximo é R$ 10.000,00")
    private Double preco;

    // Campos auxiliares para resposta de desconto
    @Transient
    private Double precoFinal;

    @Transient
    private Double descontoAplicado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
