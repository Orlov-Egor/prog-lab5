package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.NoSuchElementException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import data.SpaceMarine;

public class FileManager {

    private File marinesCollectionFromFile = new File ("marinesCollection.json");
    private Gson gson = new Gson();

    public FileManager()
    {
        
    }

    public void writeCollection(Collection<?> collection) {
    	try{
    		FileWriter collectionFileWriter = new FileWriter(marinesCollectionFromFile);
        	collectionFileWriter.write(gson.toJson(collection));
        	collectionFileWriter.flush();
        	collectionFileWriter.close();
    	}catch(IOException exception){
    		System.out.println("Ошибка записи коллекции в файл!");
    	}
    }

    public TreeSet<SpaceMarine> readCollection() {
        try(Scanner collectionFileScanner = new Scanner(marinesCollectionFromFile)){
        	TreeSet<SpaceMarine> marinesCollection;
        	Type collectionType = new TypeToken<TreeSet<SpaceMarine>>(){}.getType();
            String collectionFromFile = collectionFileScanner.nextLine();
            marinesCollection = gson.fromJson(collectionFromFile.toString(), collectionType);
            return marinesCollection;
        }
        catch(NoSuchElementException exception){
            System.out.println("В файле нет коллекции!");
        }
        catch(FileNotFoundException exception){
            System.out.println("Файл не найден!");
        }
        return new TreeSet<SpaceMarine>();
    }
}
