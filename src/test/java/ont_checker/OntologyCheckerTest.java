package ont_checker;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class OntologyCheckerTest {
    String tmpFolder = ".\\onts594684094570337625";
    String tmpFolder1 = ".\\onts9835849067569004384";
    @Test
    void ontCheckerTest() throws IOException {
        Path dirPath = Paths.get(tmpFolder1);
        OntologyChecker ontologyChecker = new OntologyChecker(dirPath);

    }
}