
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;

public class Main {

    User u1 = new User("Ivan", "mail@ru");
    static final String  mainPath = "C:\\Users\\User\\Desktop\\прог\\тпир\\test.xlsx\\xl";
    public static void main(String[] args) {

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


        User u2 = null;
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


//        Path path=Paths.get("C:\\Users\\User\\Desktop\\прог\\тпир\\test.xlsx\\xl");
        Path path=Paths.get(mainPath);
        try {
            Files.walkFileTree(path, new FileVisitor<Path>() {

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

//                    System.out.println("Post Visit Directory: "+dir.getFileName() );
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    doSpaceNio( 1, String.valueOf(dir.getParent()));
                    System.out.println(dir.getFileName());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

//                    Integer i = 1;
                   doSpaceNio( 1, String.valueOf(file.getParent()));
                    System.out.println(  file.getFileName());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
//                    System.out.println("Visit Failed File: "+file.getFileName());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
//    String begin = "C:\\Users\\User\\Desktop\\прог\\тпир\\test.xlsx\\xl";
//        processFilesFromFolder(new File(begin), begin, 0);


    private static void processFilesFromFolder(File setEntry, String path, Integer counter) {

        counter = counter + 1;
        if (setEntry.isDirectory()) {

            String newpath = (counter > 1) ? path + "\\" + setEntry.getName() : path;

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
    private static void processFilesFromFolderNio(File setEntry, String path, Integer counter) {

        counter = counter + 1;
        if (setEntry.isDirectory()) {

            String newpath = (counter > 1) ? path + "\\" + setEntry.getName() : path;

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

    private static void doSpaceNio(Integer counter, String path) {

        Path newpath =  Paths.get( path);
        if(newpath.getFileName().equals( Paths.get(  mainPath ).getParent().getFileName())){
        for (int i = 0; i < counter; i++) {
            String space = " ";
            System.out.printf("%s", space);
        }
    }
        else{
            doSpaceNio( counter + 1, String.valueOf(newpath.getParent()));
        }
    }
}


