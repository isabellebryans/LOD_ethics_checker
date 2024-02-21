package utils;

import utils.DownloadFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DownloadFileTest {
    String url = "https://www.w3.org/2000/10/swap/pim/usps.rdf";
    @Test
    void downloadOntTest () throws IOException {
        DownloadFile.downloadOntology(url);
    }
}