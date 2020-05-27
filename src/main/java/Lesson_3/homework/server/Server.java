package Lesson_3.homework.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream in = null;
        try(ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Ожидаем подключения клиента...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            while (true) {
                ObjectInputStream ois;
                String str = in.readUTF();
                if (str.split(" ")[1].equalsIgnoreCase("animal")) {
                    ois = new ObjectInputStream(in);
                    Animal animal = (Animal) ois.readObject();
                    animal.info();
                } else if (str.split(" ")[1].equalsIgnoreCase("cat")) {
                    ois = new ObjectInputStream(in);
                    Cat cat = (Cat) ois.readObject();
                    System.out.println(cat.getName());
                    cat.info();
                    cat.personalInfo();
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Сервер завершил свою работу");
        } finally {
            try {
                in.close();
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
}
