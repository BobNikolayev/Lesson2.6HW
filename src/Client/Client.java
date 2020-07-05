package Client;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

public class Client {

    public static void main(String[] args) {

        final int PORT = 1889;

        final String IP_ADDRESS = "localhost";

        Socket socket = null;

        try {
            socket = new Socket(IP_ADDRESS,PORT);
            Scanner inText = new Scanner(socket.getInputStream());

            PrintWriter outText = new PrintWriter(socket.getOutputStream(), true);

            while (true) {

                String inMsg = inText.nextLine();

                if (inMsg.equals("/end")) {
                    break;
                }

                System.out.println("Server: " + inMsg);

                Thread ClientOut = new Thread(() -> outMsg(outText));

                ClientOut.start();


            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void outMsg(PrintWriter text) {
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();

        text.println("Server: " + msg);
    }
}