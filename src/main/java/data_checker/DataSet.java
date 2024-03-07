package data_checker;

import ont_checker.Ontology;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import utils.DownloadFiles;
import utils.ExtractionMethods;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.nio.file.Path;

public class DataSet {
    private Model model;
    private Set<Property> properties;
    private Set<String> namespaces;
    private Path ontologiesFolder;
    private List<Ontology> ontologies;

    //constructor
    public DataSet(Model model) {
        this.model = model;
        this.properties = ExtractionMethods.extractProperties(model);
        this.namespaces = ExtractionMethods.extractNamespaces(properties);
        this.ontologiesFolder = DownloadFiles.createTempFolder();
    }


    private void setOntologies() throws IOException {
        for (String namespace : namespaces){
            DownloadFiles.downloadOntology(namespace, ontologiesFolder);
        }


    }


    public Set<Property> getProperties(){
        return properties;
    }

    public Model getModel() {
        return model;
    }

    public Set<String> getNamespaces() {
        return namespaces;
    }
}
