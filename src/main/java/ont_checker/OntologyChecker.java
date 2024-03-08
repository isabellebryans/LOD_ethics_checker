package ont_checker;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import utils.LoadData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class OntologyChecker {

    private Ontology[] ontologies;
    final Path directory;
    public OntologyChecker(Path folder) throws IOException {
        this.directory=folder;
        ontologies=setOntologies();

        // Delete temp folder
        //DownloadFile.removeTemporaryFolders(folder);
    }

    public void testOntologies(){

    }


    private Ontology[] setOntologies() throws IOException {
        // Create a File object for the temporary folder
        File tempFolder = new File(directory.toString());
        List<Ontology> onts = new ArrayList<>();
        // Check if the folder exists
        if (tempFolder.exists() && tempFolder.isDirectory()) {
            // List all files in the folder
            File[] files = tempFolder.listFiles();
            System.out.println("Temp folder is a directory");
            // Iterate through each file
            for (File file : files) {
                // Check if it's a file (not a directory)
                if (file.isFile()) {
                    try  {
                        String uri = LoadData.getURIfromFile(file);
                        Model m = LoadData.initAndLoadModelFromFolder(file, Lang.RDFXML);
                       // System.out.println(m);
                        Ontology ontology = new Ontology(m, uri);
                        // ADD ONTOLOGY TO ONTOLOGIES ARRAY HERE
                        onts.add(ontology);

                    } catch (IOException e) {
                        System.out.println("Couldn't get model from file");
                    }
                }
            }
        } else {
            System.out.println("Temporary folder does not exist or is not a directory.");
            return null;
        }
        return onts.toArray(new Ontology[0]);
    }


}
