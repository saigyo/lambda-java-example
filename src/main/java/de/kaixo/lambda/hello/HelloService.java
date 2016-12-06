package de.kaixo.lambda.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.Context;

import de.kaixo.lambda.support.Decryptor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class HelloService
{
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request
    {
        String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response
    {
        String message;
    }

    @Autowired
    Decryptor decryptor;

    String decrypted = null;

    public Response handle(Request request, Context context) {
        context.getLogger().log("name is " + request.getName());

        if (decrypted == null) {
            decrypted = decryptor.decryptKey(System.getenv("secret"));
        }

        return new Response("Hello, " + request.getName() + "! The secret is '" + decrypted + "'");
    }
}
