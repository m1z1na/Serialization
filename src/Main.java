
import java.io.*;

public class Main  {


    public static void main (String[] args) {


        User u1 = new User("Ivan", "mail@ru");

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("C:\\Users\\User\\Desktop\\прог\\тпир\\user.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(u1);
            out.close();
            fileOut.close();
            System.out.println("Сериализованные данные сохраняются в \\user.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }


        User u2  = null;
        try {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\User\\Desktop\\прог\\тпир\\user.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            u2 = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Класс User не найден");
            c.printStackTrace();
            return;
        }

        System.out.println("Десериализованный User:");
        System.out.println("Имя: " + u2.username);
        System.out.println("Почта: " + u2.email);



//            String begin = "C:\\Users\\User\\Desktop\\прог\\тпир\\test.xlsx\\xl";
//            processFilesFromFolder(new File(begin), begin, 0);

    }

    private static void processFilesFromFolder(File setEntry, String path, Integer counter) {

        counter = counter + 1;
        if (setEntry.isDirectory()) {

            String newpath;
            if (counter > 1) {
                newpath = path + "\\" + setEntry.getName();
            } else {
                newpath = path;
            }

            File folder = new File(newpath);
            File[] folderEntries = folder.listFiles();
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    doSpace(counter);
                    System.out.println(entry.getName());
                    processFilesFromFolder(entry, folder.getPath(), counter);
                } else {
                    doSpace(counter);
                    System.out.println(entry.getName());
                }
            }
        }
    }

    private static void doSpace(Integer counter) {
        for (int i = 0; i < counter; i++) {
            String space = " ";
            System.out.printf("%s", space);
        }
    }
}



