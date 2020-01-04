package version_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadWriteFiles {

    private Path path;

    public ReadWriteFiles(String nameFile) {
        this.path = Path.of("E:\\IT\\EPAM\\JAVA WEB\\Homework\\Day5_HavanskyKA\\src\\Task_1_v2\\final_task", nameFile);

    }

    public List<String> read() throws IOException {

        return Files.readAllLines(path);

    }

    public void write(List<String> lines) throws IOException {

        Files.write(path, lines);

    }
}
