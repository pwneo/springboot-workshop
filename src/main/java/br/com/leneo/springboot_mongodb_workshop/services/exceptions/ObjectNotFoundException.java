package br.com.leneo.springboot_mongodb_workshop.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String msg){
        super("Recurso não encontrado. Id: "+ msg);
    }
}
