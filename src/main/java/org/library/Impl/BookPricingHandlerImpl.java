package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Model.BookBorrow;
import org.library.Model.Catalog;
import org.library.Model.PricingStrategy;
import org.library.Model.PricingStrategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class BookPricingHandlerImpl implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            if("POST".equals(exchange.getRequestMethod())){
                String respText = "Pricing Updated";
                Map<String,String> attributes=BaseUnMarshalling.parse(exchange.getRequestBody());
                Catalog.updatePricingStrategy(Integer.parseInt(attributes.get("strategy")));
                exchange.sendResponseHeaders(200, respText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respText.getBytes());
                output.flush();
                exchange.close();
            }
            else if("GET".equals(exchange.getRequestMethod())){
                PricingStrategy pricingStrategy= Catalog.getPricingStrategy();
                BaseMarshalling<PricingStrategy> bs= new BaseMarshalling<>();
                exchange.getResponseHeaders().set("Content-Type","application/json");
                exchange.sendResponseHeaders(200, bs.getResponseLength(pricingStrategy));
                OutputStream os=bs.getOutputStream(exchange.getResponseBody());
                os.flush();
                exchange.close();
            }
            else {
                exchange.sendResponseHeaders(405,-1);
            }
        }
        catch (Exception e){
            exchange.getResponseHeaders().set("Content-Type","application/json");
            ExceptionHandler exceptionHandler=new ExceptionHandler(e);
            exchange.sendResponseHeaders(exceptionHandler.getErrorCode(), exceptionHandler.getResponseLength());
            OutputStream os=exceptionHandler.getResponseStream(exchange.getResponseBody());
            os.flush();
            exchange.close();
        }
    }
}
