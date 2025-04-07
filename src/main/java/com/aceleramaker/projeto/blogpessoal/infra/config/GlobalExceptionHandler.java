package com.aceleramaker.projeto.blogpessoal.infra.config;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String ERROR_MESSAGE = "Ocorreu um erro: ";

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail defaultExceptionHandler(Exception ex) {
        return getProblemDetail(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno no servidor", ex);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        return getProblemDetail(HttpServletResponse.SC_BAD_REQUEST, ERROR_MESSAGE + ex.getMessage(), ex);
    }

    @ExceptionHandler(value = { NoResourceFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNoResourceFoundException(NoResourceFoundException ex) {
        return getProblemDetail(HttpServletResponse.SC_NOT_FOUND, "Recurso não encontrado", ex);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return handleBeanValidationException(ex);
    }

    @ExceptionHandler(value = { HandlerMethodValidationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleHandlerMethodValidation(HandlerMethodValidationException ex) {
        return handleBeanValidationException(ex);
    }

//    @ExceptionHandler(value = { ExpiredJwtException.class })
//    @ResponseStatus(HttpStatus.UNAUTHORIZED) // Changed from custom 498 to standard 401
//    public ProblemDetail handleExpiredJwtException(ExpiredJwtException ex) {
//        return getProblemDetail(HttpServletResponse.SC_UNAUTHORIZED, "Token expirado ou inválido.", ex);
//    }

    @ExceptionHandler(value = { UsernameNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND) // Standard Spring Security behavior maps this often to 401, but 404 can also be valid
    public ProblemDetail handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return getProblemDetail(HttpServletResponse.SC_NOT_FOUND, ex.getMessage(), ex);
    }

    @ExceptionHandler(value = { BadCredentialsException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException ex) {
        return getProblemDetail(HttpServletResponse.SC_UNAUTHORIZED, "Credenciais inválidas", ex);
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex) {
        return getProblemDetail(HttpServletResponse.SC_FORBIDDEN, "Acesso negado.", ex);
    }

    private ProblemDetail getProblemDetail(int statusCode, String defaultDetail, Exception ex) {
        String detailMessage = ex.getMessage() != null ? ex.getMessage() : defaultDetail;
        log.error("{}: {}", defaultDetail, detailMessage, ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(statusCode), detailMessage);
        return problemDetail;
    }

    private ProblemDetail handleBeanValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setTitle("Erro de validação");
        detail.setDetail("Um ou mais campos são inválidos.");
        detail.setProperty("errors", errors);
        log.warn("Erro de validação (MethodArgumentNotValidException): {}", errors);
        return detail;
    }

    private ProblemDetail handleBeanValidationException(HandlerMethodValidationException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getAllValidationResults().forEach(result -> {
            String parameterName = result.getMethodParameter().getParameterName();
            result.getResolvableErrors().forEach(error -> {
                String fieldName = parameterName;
                if (error.getCodes() != null && error.getCodes().length > 0) {
                    fieldName = error.getCodes()[error.getCodes().length - 1];
                    if (fieldName.contains(".")) {
                        fieldName = fieldName.substring(fieldName.lastIndexOf('.') + 1);
                    }
                }
                errors.put(fieldName, error.getDefaultMessage());
            });

        });

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setTitle("Erro de validação");
        detail.setDetail("Um ou mais parâmetros do método são inválidos.");
        detail.setProperty("errors", errors);
        log.warn("Erro de validação (HandlerMethodValidationException): {}", errors);
        return detail;
    }
}
