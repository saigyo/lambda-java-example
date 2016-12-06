package de.kaixo.lambda.http;

import lombok.Data;

import java.util.Collections;
import java.util.Map;

@Data
public class HttpRequest
{
    String resource;
    String path;
    String httpMethod;
    Map<String, String> headers = Collections.emptyMap();
    Map<String, String> queryStringParameters = Collections.emptyMap();
    Map<String, String> pathParameters = Collections.emptyMap();
    Map<String, String> stageVariables = Collections.emptyMap();
    String body;
    boolean base64Encoded;
}
