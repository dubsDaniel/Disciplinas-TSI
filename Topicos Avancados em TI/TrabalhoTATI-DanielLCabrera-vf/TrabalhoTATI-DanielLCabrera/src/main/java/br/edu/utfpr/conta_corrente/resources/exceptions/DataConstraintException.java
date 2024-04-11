package br.edu.utfpr.conta_corrente.resources.exceptions;

public class DataConstraintException extends RuntimeException{
    public DataConstraintException(String mensagem) {
        super(mensagem);
    }
}
