
package de.kaixo.lambda.hello;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import de.kaixo.lambda.http.HttpRequest;
import de.kaixo.lambda.http.HttpResponse;
import de.kaixo.lambda.support.AbstractHandler;

public class HelloLambda extends AbstractHandler<HelloConfiguration>
        implements RequestHandler<HttpRequest, HttpResponse>
{
    @Override
    public HttpResponse handleRequest(HttpRequest httpRequest, Context context)
    {
        HelloController helloController = getApplicationContext().getBean(HelloController.class);
        context.getLogger().log("Input: " + httpRequest);
        return helloController.handle(httpRequest, context);
    }
}