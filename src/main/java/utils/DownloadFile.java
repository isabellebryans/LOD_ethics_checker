package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class DownloadFile {
    private static Path tmpFolder;
    private static final Logger logger = LoggerFactory.getLogger(DownloadFile.class);
    public static void downloadOntology(String ontURL) throws IOException {
        // Create temp folder
        tmpFolder = null;
        try{
            tmpFolder = Files.createTempDirectory(Path.of("."), "onts");
        } catch(Exception e){
            logger.error("Could not create temporary folder. Exiting");
            return;
        }

        String ontologyPath = tmpFolder + File.separator + "ontology.rdf";
        downloadFile(ontURL, ontologyPath);
        logger.info("Loading ontology ");

    }


    public static void downloadFile(String fileURL, String savePath) throws IOException {
        URL url = new URL(fileURL);
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();

        try (FileOutputStream outputStream = new FileOutputStream(savePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }


}
