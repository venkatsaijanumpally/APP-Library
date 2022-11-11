package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Impl.BaseUnMarshalling;
import org.library.Model.Catalog;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class BookPricingUpdateRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String respText = "Pricing Updated";
        Map<String,String> attributes= BaseUnMarshalling.parse(exchange.getRequestBody());
        Catalog.updatePricingStrategy(Integer.parseInt(attributes.get("strategy")));
        exchange.sendResponseHeaders(200, respText.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(respText.getBytes());
        output.flush();
        exchange.close();
    }
}
