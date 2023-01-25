package org.JKTM2_0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;


    public Server(int port) throws IOException {
        try{
            serverSocket = new ServerSocket(port);
            serverSocket.getInetAddress();
            System.out.println("Server started");

            while(true){
                Socket client = serverSocket.accept();
                client.setKeepAlive(true);
                System.out.println("Client " + client.getInetAddress().getHostAddress());

                Client clientSock =  new Client(client);

                new Thread(clientSock).start();
            }

        }
        catch(IOException ioException){
            throw ioException;
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
