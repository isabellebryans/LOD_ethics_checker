package data_checker;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import utils.ExtractionMethods;

import java.util.Set;

public class DataSet {
    private Model model;
    private Set<Property> properties;
    private Set<String> namespaces;

    //constructor
    public DataSet(Model model) {
        this.model = model;
        this.properties = ExtractionMethods.extractProperties(model);
        this.namespaces = ExtractionMethods.extractNamespaces(properties);
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
