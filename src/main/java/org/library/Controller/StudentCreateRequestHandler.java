package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Impl.BaseUnMarshalling;
import org.library.Model.Student;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class StudentCreateRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String respText = "Created Student";
        Map<String,String> attributes= BaseUnMarshalling.parse(exchange.getRequestBody());
        new Student(attributes);
        exchange.sendResponseHeaders(200, respText.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(respText.getBytes());
        output.flush();
        exchange.close();
    }
}
