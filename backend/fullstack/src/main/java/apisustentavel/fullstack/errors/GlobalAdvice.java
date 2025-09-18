
package apisustentavel.fullstack.errors;

import apisustentavel.fullstack.dtos.responses.ErrorResponseDto;
import apisustentavel.fullstack.errors.exceptions.ClientExistsException;
import apisustentavel.fullstack.errors.exceptions.SustainableActionGenericExeption;
import apisustentavel.fullstack.errors.exceptions.SustainableActionNotFoundException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalAdvice
{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
    {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(new ErrorResponseDto(
                "400",
                "Erro ao validar corpo da requisição",
                String.join(", ", errors),
                ex.getClass().getSimpleName()
        ));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUsernameNotFound(UsernameNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResponseDto(
                        "401",
                        ex.getMessage(),
                        null,
                        ex.getClass().getSimpleName()
                )
        );
    }

    @ExceptionHandler(ClientExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleClientExists(ClientExistsException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDto(
                        "409",
                        ex.getMessage(),
                        null,
                        ex.getClass().getSimpleName()
                )
        );
    }

    @ExceptionHandler(SustainableActionNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(SustainableActionNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponseDto(
                        "404",
                        ex.getMessage(),
                        null,
                        ex.getClass().getSimpleName()
                )
        );
    }

    @ExceptionHandler(SustainableActionGenericExeption.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(SustainableActionGenericExeption ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDto(
                        "500",
                        "Ocorreu um erro interno no servidor",
                        ex.getMessage(),
                        ex.getClass().getSimpleName()
                )
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorDetail;

        assert ex.getRequiredType() != null;
        if (ex.getRequiredType().isEnum()) {
            errorDetail = String.format("O parâmetro '%s' recebeu o valor '%s', que é inválido. Valores aceitos: %s",
                    ex.getName(), ex.getValue(), Arrays.toString(ex.getRequiredType().getEnumConstants()));
        } else {
            errorDetail = String.format("O parâmetro '%s' recebeu o valor '%s', que é de um tipo inválido. Tipo esperado: %s",
                    ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        }

        return ResponseEntity.badRequest().body(
                new ErrorResponseDto(
                        "400",
                        "Erro ao validar parâmetros da requisição",
                        errorDetail,
                        ex.getClass().getSimpleName()
                )
        );
    }

}
