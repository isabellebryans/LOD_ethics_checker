package data_checker;

import ont_checker.Ontology;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import utils.DownloadFiles;
import utils.ExtractionMethods;
import utils.LoadData;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.nio.file.Path;

public class DataSet {
    private Model model;
    private Set<Property> properties;
    private List<Namespace> namespaces;
    private Path ontologiesFolder;
    private Ontology[] ontologies;

    //constructor
    public DataSet(Model model) throws IOException {
        this.model = model;
        this.properties = ExtractionMethods.extractProperties(model);
        this.namespaces = ExtractionMethods.extractNamespaces(properties);
        this.ontologiesFolder = downloadOntologies();
        this.ontologies = LoadData.loadOntologiesFromFolder(this.ontologiesFolder);
        //DownloadFiles.removeTemporaryFolders(this.ontologiesFolder);
    }


    private Path downloadOntologies() {
        Path folder = DownloadFiles.createTempFolder();
        System.out.println("Temp folder created is "+folder.toString());
        for (Namespace namespace : namespaces){
            try {
                DownloadFiles.downloadOntology(namespace.ns, folder);
                namespace.setDownloadable(true);
            } catch (Exception e){
                System.out.println("Couldn't download ontology "+namespace);
            }
        }
        return folder;
    }

    public Set<Property> getProperties(){
        return properties;
    }

    public Model getModel() {
        return model;
    }

    public Ontology[] getOntologies(){
        return ontologies;
    }

    public List<Namespace> getNamespaces() {
        return namespaces;
    }
}
