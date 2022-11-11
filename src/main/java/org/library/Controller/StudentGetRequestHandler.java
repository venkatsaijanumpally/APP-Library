package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Impl.BaseMarshalling;
import org.library.Model.Student;

import java.io.IOException;
import java.io.OutputStream;

public class StudentGetRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Iterable<Student> students=Student.getStudents();
        BaseMarshalling<Student> bs= new BaseMarshalling<>();
        exchange.getResponseHeaders().set("Content-Type","application/json");
        exchange.sendResponseHeaders(200, bs.getResponseLength(students, "students"));
        OutputStream os=bs.getOutputStream(exchange.getResponseBody());
        os.flush();
        exchange.close();
    }
}
