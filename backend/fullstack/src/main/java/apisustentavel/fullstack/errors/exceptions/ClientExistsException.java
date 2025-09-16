package apisustentavel.fullstack.errors.exceptions;

public class ClientExistsException extends RuntimeException
{
    public ClientExistsException(String message)
    {
        super(message);
    }
}
