package Lesson_3.homework.client;

import Lesson_3.homework.server.Animal;
import Lesson_3.homework.server.Cat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static Socket socket;

    public static void main(String[] args) {
        try {
            startClient();
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            closeConnection();
        }
    }

    static void startClient() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            str = sc.nextLine();
            if (str.equalsIgnoreCase("end")) {
                break;
            }
            if (str.contains("cat")) {
                out.writeUTF(str);
                Cat s = new Cat("Bob", 2);
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(s);
            } else if (str.contains("animal")) {
                out.writeUTF(str);
                Animal s = new Animal();
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(s);
            }
        }
    }

    private static void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
