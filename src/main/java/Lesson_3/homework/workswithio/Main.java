package Lesson_3.homework.workswithio;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // 1 ----------------
//        try(FileInputStream fis = new FileInputStream("123/2.txt")) {
//            int x;
//            byte[] arr = new byte[512];
//            while ((x = fis.read(arr)) > 0) {
//                System.out.print(new String(arr, 0, x, "UTF-8"));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 2 ----------------
//        ArrayList<InputStream> streamArrayList = new ArrayList<>();
//        streamArrayList.add(new FileInputStream("123/1.txt"));
//        streamArrayList.add(new FileInputStream("123/2.txt"));
//        streamArrayList.add(new FileInputStream("123/3.txt"));
//        streamArrayList.add(new FileInputStream("123/4.txt"));
//        streamArrayList.add(new FileInputStream("123/5.txt"));
//
//        try(SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(streamArrayList))) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(sequenceInputStream));
//            String currentLine;
//            while ((currentLine = reader.readLine()) != null) {
//                System.out.println(currentLine);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 3 ----------------- Think about how to improve code, when text both latin or cyrillic (not to hardcode amount of bytes)
//        File file = new File("123/task3.txt");
        Scanner sc = new Scanner(System.in);
        int numberOfCharsInOnePage = 3600;
        try (RandomAccessFile raf = new RandomAccessFile("123/task3.txt", "r")) {
            byte[] byteArray;
            while (true) {
                int pageNumber = sc.nextInt();
                if (pageNumber == -1) break;
                if (pageNumber == 1) {
                    raf.seek(0);
                } else if (pageNumber > 1) {
                    raf.seek((pageNumber - 1) * numberOfCharsInOnePage);
                }
                byteArray = new byte[numberOfCharsInOnePage];
                raf.read(byteArray);
                System.out.print(new String(byteArray));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
