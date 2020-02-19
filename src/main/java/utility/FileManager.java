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
import com.google.gson.JsonParseException;

import data.SpaceMarine;

public class FileManager {
    private Gson gson = new Gson();
    private File collectionFile;

    public FileManager() {
        collectionFile = new File("marinesCollection.json");
    }

    public void writeCollection(Collection<?> collection) {
    	try (FileWriter collectionFileWriter = new FileWriter(collectionFile)) {
        	collectionFileWriter.write(gson.toJson(collection));
    	} catch (IOException exception) {
    		System.out.println("Загрузочный файл является директорией/не может быть открыт!");
    	}
    }

    public TreeSet<SpaceMarine> readCollection() {
        try (Scanner collectionFileScanner = new Scanner(collectionFile)) {
        	TreeSet<SpaceMarine> collection;
            Type collectionType = new TypeToken<TreeSet<SpaceMarine>>(){}.getType();
            collection = gson.fromJson(collectionFileScanner.nextLine().trim(), collectionType);
            System.out.println("Коллекция успешна загружена!");
            return collection;
        } catch (NoSuchElementException exception) {
            System.out.println("Загрузочный файл пуст!");
        } catch (FileNotFoundException exception) {
            System.out.println("Загрузочный файл не найден!");
        } catch (JsonParseException exception) {
            System.out.println("В загрузочном файле не обнаружена коллекция!");
        } catch (IllegalStateException exception) {
            System.out.println("Непредвиденная ошибка!");
            System.exit(0);
        }
        return new TreeSet<SpaceMarine>();
    }
}
