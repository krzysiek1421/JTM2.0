package org.JKTM2_0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {
    private boolean buttonStatus;
    public static boolean authorizationStatus = false;
    private final Socket clientSocket;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Client(Socket socket) throws IOException {
        this.clientSocket = socket;
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
    }

    public void run(){
        try{
            while(true){
                buttonStatus = getInputStream().readBoolean();
                if(isButtonStatus() && !authorizationStatus){
                    setAuthorizationStatus(true);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }
    public synchronized boolean isAuthorizationStatus() {
        return authorizationStatus;
    }

    public void setAuthorizationStatus(boolean authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }

    public synchronized boolean isButtonStatus() {
        return buttonStatus;
    }
}
