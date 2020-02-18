package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;
import java.lang.reflect.*;
import com.google.gson.*;

import com.google.gson.reflect.TypeToken;

import data.SpaceMarine;

import com.google.gson.Gson;

public class FileManager {

    private TreeSet<SpaceMarine> marinesCollection =  new TreeSet<>();

    private Gson gson = new Gson();

    public FileManager()
    {
        
    }

    public void writeCollection(Collection<?> collection) throws IOException {
        FileWriter collectionFileWriter = new FileWriter(new File("marinesCollection.json"));
        collectionFileWriter.write(gson.toJson(collection));
        collectionFileWriter.flush();
        collectionFileWriter.close();
    }

    public String readCollection() {
        String collectionFromFile = "";
        try(Scanner collectionFileScanner = new Scanner(new File("marinesCollection.json"))){
            
            collectionFromFile = collectionFileScanner.nextLine();
        }
        catch(IOException exception){
            System.out.println(" Ошибка записи файла с коллекцией!");
            System.exit(0);
        }
        return collectionFromFile;
    }
}
