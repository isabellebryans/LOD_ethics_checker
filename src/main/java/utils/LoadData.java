package utils;
import org.apache.jena.base.Sys;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoadData {

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