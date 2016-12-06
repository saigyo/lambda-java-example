package de.kaixo.lambda.hello;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.kaixo.lambda.http.HttpRequest;
import de.kaixo.lambda.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class HelloController
{
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    HelloService helloService;

    public HttpResponse handle(HttpRequest httpRequest, Context context) {
        HttpResponse httpResponse = new HttpResponse();

        HelloService.Request request;
        try {
            request = objectMapper.readerFor(HelloService.Request.class).readValue(httpRequest.getBody());
        } catch (IOException e) {
            httpResponse.setStatusCode(HttpStatus.SC_BAD_REQUEST);
            httpResponse.setBody(e.getLocalizedMessage());
            return httpResponse;
        }

        HelloService.Response response = helloService.handle(request, context);

        String body;
        try {
            body = objectMapper.writerFor(HelloService.Response.class).writeValueAsString(response);
        } catch (JsonProcessingException e) {
            httpResponse.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            httpResponse.setBody(e.getLocalizedMessage());
            return httpResponse;
        }

        httpResponse.setBody(body);
        httpResponse.setStatusCode(HttpStatus.SC_OK);
        httpResponse.getHeaders().put("Content-Type", "application/json");

        return httpResponse;
    }
}
