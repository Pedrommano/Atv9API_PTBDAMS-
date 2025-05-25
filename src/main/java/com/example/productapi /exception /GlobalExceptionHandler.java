package com.seuprojeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🟥 Recurso não encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), "Recurso não encontrado");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 🟧 Regra de negócio violada
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorDetails> handleBusinessException(BusinessRuleException ex) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), "Regra de negócio");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 🟨 Erros de validação (Bean Validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(erro -> {
            String campo = ((FieldError) erro).getField();
            String mensagem = erro.getDefaultMessage();
            erros.put(campo, mensagem);
        });
        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }

    // 🟦 Erros genéricos
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception ex) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), "Erro interno");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
