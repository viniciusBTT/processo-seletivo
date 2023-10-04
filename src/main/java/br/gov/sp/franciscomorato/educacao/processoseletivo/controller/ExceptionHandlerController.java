
package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author thiago
 */
@RestControllerAdvice
public class ExceptionHandlerController 
{
    
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public String handleAccessDenied(AccessDeniedException e)
    {
        return "Erro FORBIDDEN";
    }
    
}
