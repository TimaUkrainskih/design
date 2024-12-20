package ru.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream())
                     )) {
                    String request = input.readLine();
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (request.contains("/?msg=Bye")) {
                        server.close();
                    } else if (request.contains("/?msg=Hello")) {
                        output.write("Hello".getBytes());
                    } else {
                        output.write("What".getBytes());
                    }
                    System.out.println(request);
                    for (String string = input.readLine(); string != null &&
                            !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                } catch (IOException e) {
                    LOG.error("Socket access error", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Server socket error", e);
        }
    }
}
