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
        try{
            if("POST".equals(exchange.getRequestMethod())){
                String respText = "Created Student";
                Map<String,String> attributes=BaseUnMarshalling.parse(exchange.getRequestBody());
                new Student(attributes);
                exchange.sendResponseHeaders(200, respText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respText.getBytes());
                output.flush();
                exchange.close();
            }
            else if("GET".equals(exchange.getRequestMethod())){
                Iterable<Student> students=Student.getStudents();
                BaseMarshalling<Student> bs= new BaseMarshalling<>();
                exchange.getResponseHeaders().set("Content-Type","application/json");
                exchange.sendResponseHeaders(200, bs.getResponseLength(students, "students"));
                OutputStream os=bs.getOutputStream(exchange.getResponseBody());
                os.flush();
                exchange.close();
            }
            else if("DELETE".equals(exchange.getRequestMethod())){
                Map<String,String> attributes=BaseUnMarshalling.parse(exchange.getRequestBody());
                BaseMarshalling<Student> bs= new BaseMarshalling<>();
                Student deletedStudent=Student.deleteStudent(Integer.parseInt(attributes.get("id")));
                exchange.getResponseHeaders().set("Content-Type","application/json");
                exchange.sendResponseHeaders(200, bs.getResponseLength(deletedStudent));
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
