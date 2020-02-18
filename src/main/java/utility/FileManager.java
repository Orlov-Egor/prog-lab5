package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

import com.google.gson.Gson;

public class FileManager {
    private FileWriter collectionFileWriter;
    private Scanner collectionFilScanner;
    private Gson gson;

    public FileManager(FileWriter collectionFileWriter, Scanner collectionFileScanner, Gson gson)
    {
        this.collectionFileWriter = collectionFileWriter;
        this.collectionFilScanner = collectionFileScanner;
        this.gson = gson;
    }

    public void writeCollection(Collection<?> collection) throws IOException {
        collectionFileWriter.write(gson.toJson(collection));
        collectionFileWriter.flush();
    }

    private void readCollection() {
        // TODO: Загрузка коллекции из файла
    }
}
