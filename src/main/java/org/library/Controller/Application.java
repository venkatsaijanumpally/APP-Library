package org.library.Controller;

import com.sun.net.httpserver.HttpServer;
import org.library.Impl.*;
import org.library.Model.ScheduledTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws IOException {
        int serverPort = ConstantValues.SERVER_PORT;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor=new ScheduledThreadPoolExecutor(2);
        Runnable scheduledTask=new ScheduledTask();
        scheduledThreadPoolExecutor.schedule(scheduledTask, ConstantValues.SCHEDULED_UPDATE_INTERVAL, TimeUnit.SECONDS);

        server.createContext("/student", new StudentHandler());

        server.createContext("/student/borrow", new StudentBorrowHandler());

        server.createContext("/student/borrow/deny", new StudentBorrowDenyHandler());

        server.createContext("/book", new BookHandler());

        server.createContext("/book/pricing", new BookPricingHandlerImpl());

        server.setExecutor(null); // creates a default executor
        server.start();
    }
}