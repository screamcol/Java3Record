package Lesson_3.classwork;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("123");

        //-----------------------------
//        file.mkdirs();
//        String[] str = file.list();
//        for (String o : str) {
//            System.out.println(o);
//        }

        //------------------------------
//        String[] str = file.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.startsWith("1");
//            }
//        });
//
//        for (String o : str) {
//            System.out.println(o);
//        }

        //-----------------------------------
//        File nf = new File("123/test.txt");
//        if (!nf.exists()) {
//            throw new IOException("File not exist");
//        }
//        File nf1 = new File("123/test1.txt");
//        nf.renameTo(nf1);
//        nf.createNewFile();

        //----------------------------------
//        long t = System.currentTimeMillis();
//        try(FileInputStream in = new FileInputStream("123/2.txt")) {
//            byte[] arr = new byte[512];
//            int x;
//            while ((x = in.read(arr)) > 0) {
//                System.out.print(new String(arr, 0, x, "UTF-8"));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(System.currentTimeMillis() - t);

        //----------------------------------
//        try (InputStreamReader isr = new InputStreamReader(new FileInputStream("123/2.txt"), "UTF-8")) {
//            int x;
//            while ((x = isr.read()) != -1) {
//                System.out.print((char)x);
//            }
//        }

        //----------------------------------
//        BufferedReader bufferedReader = null;
//        FileReader fileReader = null;
//
//        fileReader = new FileReader("123/2.txt");
//        bufferedReader = new BufferedReader(fileReader);
//        String currentLine;
//        while ((currentLine = bufferedReader.readLine()) != null) {
//            System.out.println(currentLine);
//        }
//
//        if (bufferedReader != null) {
//            bufferedReader.close();
//        }
//
//        if (fileReader != null) {
//            fileReader.close();
//        }

        //------------------------------
//        PipedInputStream in = null;
//        PipedOutputStream out = null;
//
//        try {
//            in = new PipedInputStream();
//            out = new PipedOutputStream();
//            out.connect(in);
//
//            for (int i = 0; i < 100; i++) {
//                out.write(i);
//            }
//
//            int x;
//            while ((x = in.read()) != -1) {
//                System.out.println(x + " ");
//            }
//            in.close();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //-------------------------------
//        ArrayList<InputStream> ali = new ArrayList<>();
//        ali.add(new FileInputStream("123/2.txt"));
//        ali.add(new FileInputStream("123/5.txt"));
//        ali.add(new FileInputStream("123/12.txt"));
//
//        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));
//
//        int x;
//        while ((x = in.read()) != -1) {
//            System.out.print((char)x);
//        }
//        in.close();

        //----------------------------------
//        try (RandomAccessFile raf = new RandomAccessFile("123/2.txt", "r")) {
//            raf.seek(0);
//            System.out.println((char)raf.read());
//        }

        //----------------------------------
//        Students s = new Students(1, "Bob");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stud.ser"));
//        oos.writeObject(s);
//        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stud.ser"));
        Students s2 = (Students)ois.readObject();
        ois.close();
        s2.info();
    }
}
