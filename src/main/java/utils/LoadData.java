package utils;
import ont_checker.Ontology;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LoadData {

    public static Ontology[] loadOntologiesFromFolder(Path folder) throws IOException {
        // Create a File object for the temporary folder
        File tempFolder = new File(folder.toString());
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
                        if (m != null){
                            // System.out.println(m);
                            Ontology ontology = new Ontology(m, uri);
                            // ADD ONTOLOGY TO ONTOLOGIES ARRAY HERE
                            onts.add(ontology);
                        }
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

    public static String getURIfromFile(File file) throws IOException{
        try {
            InputStream tempIn = new FileInputStream(file);
            String uri = readUntilDelimiter(tempIn, '>');
            tempIn.close();
            return shave_uri(uri);
        }
        catch(Exception e){
            System.out.println("Couldn't get URI");
        }
        return null;
    }

    private static String shave_uri(String input){
        // Check if the string starts with "<!--" and ends with "-->"
        if (input.startsWith("<!--") && input.endsWith("-->")) {
            // Remove the first 4 characters "<!--" and the last 3 characters "-->"
            String result = input.substring(4, input.length() - 3);
            return result;
        } else {
            System.out.println("The input string does not have the expected format.");
            return input;
        }
    }

    private static String readUntilDelimiter(InputStream inputStream, char delimiter) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int character;

        while ((character = inputStream.read()) != -1) {
            char currentChar = (char) character;

            // Append the character to the StringBuilder
            stringBuilder.append(currentChar);

            // Check if the delimiter character is encountered
            if (currentChar == delimiter) {
                break; // Stop reading if the delimiter is found
            }
        }

        return stringBuilder.toString();
    }

    public static Model initAndLoadModelFromFolder(File newFile, Lang lang) throws IOException {
        // Turn file into input stream to be read
        try {
            InputStream dataModelIS = new FileInputStream(newFile);
            // Create empty RDF model
            Model dataModel = ModelFactory.createDefaultModel();
            // From RDF io lib (riot), Read input stream into new model
            RDFDataMgr.read(dataModel, dataModelIS, lang);
            return dataModel;
        } catch(Exception e){
            System.out.println("Couldn't load model");
        }
        return null;
    }

    public static Model initAndLoadModelFromResource(String filename, Lang lang) throws IOException {
        // Turn file into input stream to be read
        File newFile = new File(filename);
        InputStream dataModelIS = LoadData.class.getClassLoader().getResourceAsStream(filename);
        //InputStream dataModelIS = new FileInputStream(newFile);
        // Create empty RDF model
        Model dataModel = ModelFactory.createDefaultModel();
        // From RDF io lib (riot), Read input stream into new model
        RDFDataMgr.read(dataModel, dataModelIS, lang);
        return dataModel;
    }
}