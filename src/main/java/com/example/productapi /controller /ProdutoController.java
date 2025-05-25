package com.seuprojeto.controller;

import com.seuprojeto.dto.ProdutoDTO;
import com.seuprojeto.dto.ProdutoDescontoDTO;
import com.seuprojeto.model.Produto;
import com.seuprojeto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pr
