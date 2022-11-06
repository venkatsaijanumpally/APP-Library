package org.library.Impl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Model.Student;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class StudentHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            String respText = "Created Student";
            exchange.sendResponseHeaders(200, respText.getBytes().length);
            Map<String,String> attributes=BaseUnMarshalling.parse(exchange.getRequestBody());
            new Student(attributes);
            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }
        else if("GET".equals(exchange.getRequestMethod())){
            Iterable<Student> students=Student.getStudents();
            BaseMarshalling<Student> bs= new BaseMarshalling<>();
            //OutputStream os=bs.objectListToStream(students,exchange.getResponseBody());
            exchange.getResponseHeaders().set("Content-Type","application/json");
            exchange.sendResponseHeaders(200, bs.getResponseLength(students, "students"));
            OutputStream os=bs.getOutputStream(exchange.getResponseBody());
            os.flush();
            exchange.close();
        }
        else {
            exchange.sendResponseHeaders(405,-1);
        }
    }
}
