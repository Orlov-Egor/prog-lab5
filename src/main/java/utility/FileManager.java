package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

import com.google.gson.Gson;

public class FileManager {
    private FileWriter collectionFileWriter;
    private Gson gson;

    public FileManager(FileWriter collectionFileWriter, Gson gson)
    {
        this.collectionFileWriter = collectionFileWriter;
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
