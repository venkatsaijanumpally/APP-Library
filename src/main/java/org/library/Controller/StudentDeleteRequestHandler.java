package org.library.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.library.Impl.BaseMarshalling;
import org.library.Impl.BaseUnMarshalling;
import org.library.Model.Student;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class StudentDeleteRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String,String> attributes= BaseUnMarshalling.parse(exchange.getRequestBody());
        BaseMarshalling<Student> bs= new BaseMarshalling<>();
        Student deletedStudent=Student.deleteStudent(Integer.parseInt(attributes.get("id")));
        exchange.sendResponseHeaders(200, bs.getResponseLength(deletedStudent));
        OutputStream os=bs.getOutputStream(exchange.getResponseBody());
        os.flush();
        exchange.close();
    }
}
