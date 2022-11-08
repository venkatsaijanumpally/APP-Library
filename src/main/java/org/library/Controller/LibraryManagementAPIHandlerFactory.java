package org.library.Controller;

import com.sun.net.httpserver.HttpHandler;
import org.library.Impl.StudentHandler;

import java.util.HashMap;
import java.util.Map;

public class LibraryManagementAPIHandlerFactory {

    Map<String, HttpHandler> apiToHandlerMap = new HashMap<>();

    public LibraryManagementAPIHandlerFactory(){
        apiToHandlerMap.put("", new StudentHandler());
    }
}
