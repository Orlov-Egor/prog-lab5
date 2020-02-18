package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

import com.google.gson.Gson;

public class FileManager {
    private Gson gson;

    public FileManager(Gson gson)
    {
        this.gson = gson;
    }

    public void writeCollection(Collection<?> collection) {
        try (FileWriter collectionFileWriter = new FileWriter(new File("marinesCollection.json"))){
            collectionFileWriter.write(gson.toJson(collection));
            collectionFileWriter.flush();
        }catch (IOException exception) {
            System.out.println(" Ошибка чтения файла с коллекцией!");
            System.exit(0);
        }
    }

    public String readCollection() {
        String collectionFromFile = "";
        try(Scanner collectionFileScanner = new Scanner(new File("marinesCollection.json"))){
            collectionFromFile = collectionFileScanner.nextLine();
        }catch(IOException exception){
            System.out.println(" Ошибка записи файла с коллекцией!");
            System.exit(0);
        }
        return collectionFromFile;
    }
}
