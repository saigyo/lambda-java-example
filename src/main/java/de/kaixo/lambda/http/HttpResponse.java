package de.kaixo.lambda.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
public class HttpResponse
{
    String body;
    Map<String, String> headers = new HashMap<>();
    int statusCode;
    boolean base64Encoded;
}
