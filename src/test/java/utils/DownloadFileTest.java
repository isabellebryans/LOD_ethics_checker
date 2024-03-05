package utils;

import ont_checker.FoopsChecker;
import utils.DownloadFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DownloadFileTest {
    String[] namespaces= {"http://www.w3.org/1999/02/22-rdf-syntax-ns#",
            "http://www.w3.org/2003/01/geo/wgs84_pos#",
            "https://opendata.camden.gov.uk/resource/qeje-7ve7/",
            "http://www.w3.org/2000/01/rdf-schema#",
            "http://www.w3.org/2000/10/swap/pim/usps#",
            "http://www.socrata.com/rdf/terms#",
            "http://www.w3.org/1999/02/22-rdf-syntax-ns#"};
    String url = "https://www.w3.org/2000/10/swap/pim/usps";
    String url1 = "https://www.w3.org/2003/01/geo/wgs84_pos";
    @Test
    void downloadOntTest () throws IOException {
        Path folder = DownloadFile.createTempFolder();
//        DownloadFile.downloadOntology(url1);
//        DownloadFile.downloadOntology(url);
//        DownloadFile.downloadOntology(namespaces[4]);
        for (String ont : namespaces) {
            System.out.println(ont);
            // Now download ontologies used, and run queries on them
            DownloadFile.downloadOntology(ont, folder);

        }
        DownloadFile.removeTemporaryFolders(folder);
    }

}