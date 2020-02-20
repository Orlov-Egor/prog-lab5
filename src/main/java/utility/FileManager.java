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

/**
 * Operates the file to save/load collection.
 */
public class FileManager {
    private Gson gson = new Gson();
    private String envVariable;

    public FileManager(String envVariable) {
        this.envVariable = envVariable;
    }

    /**
     * Writes collection to a file.
     * @param collection Collection to write.
     */
    public void writeCollection(Collection<?> collection) {
    	try (FileWriter collectionFileWriter = new FileWriter(new File(System.getenv().get(envVariable)))) {
        	collectionFileWriter.write(gson.toJson(collection));
    	} catch (NullPointerException exception) {
            System.out.println("Системная переменная с загрузочным файлом не найдена!");
        } catch (IOException exception) {
    		System.out.println("Загрузочный файл является директорией/не может быть открыт!");
    	}
    }

    /**
     * Reads collection from a file.
     * @return Readed collection.
     */
    public TreeSet<SpaceMarine> readCollection() {
        try (Scanner collectionFileScanner = new Scanner(new File(System.getenv().get(envVariable)))) {
        	TreeSet<SpaceMarine> collection;
            Type collectionType = new TypeToken<TreeSet<SpaceMarine>>(){}.getType();
            collection = gson.fromJson(collectionFileScanner.nextLine().trim(), collectionType);
            System.out.println("Коллекция успешна загружена!");
            return collection;
        } catch (NullPointerException exception) {
            System.out.println("Системная переменная с загрузочным файлом не найдена!");
        } catch (FileNotFoundException exception) {
            System.out.println("Загрузочный файл не найден!");
        } catch (NoSuchElementException exception) {
            System.out.println("Загрузочный файл пуст!");
        } catch (JsonParseException exception) {
            System.out.println("В загрузочном файле не обнаружена коллекция!");
        } catch (IllegalStateException exception) {
            System.out.println("Непредвиденная ошибка!");
            System.exit(0);
        }
        return new TreeSet<SpaceMarine>();
    }

    @Override
    public String toString() {
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}
