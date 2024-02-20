package data_checker;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import java.util.HashSet;
import java.util.Set;

public class DataChecker {
    private Model model;
    private Set<Property> properties;
    private Set<String> namespaces;

    //constructor
    public DataChecker(Model m) {
        model = m;
        setProperties(model);
        extractNamespaces();
    }



    private void setProperties(Model model){
        // Collect unique properties
        Set<Property> properties1 = new HashSet<>();
        StmtIterator iter = model.listStatements();
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Property property = stmt.getPredicate();
            properties1.add(property);
        }

        // Print the unique properties
        System.out.println("Unique Properties:");
        for (Property property : properties1) {
            System.out.println(property.getURI());
        }
        properties = properties1;
    }
    private void extractNamespaces() {
        namespaces = new HashSet<>();
        for (Property property : properties) {
            String namespace = extractNamespace(property.getURI());
            namespaces.add(namespace);
        }
        System.out.println("Namespaces:");
        for (String ns : namespaces) {
            System.out.println(ns);
        }
    }

    private String extractNamespace(String uri) {
        // Find the index of the last occurrence of '/' or '#'
        int lastSlashIndex = uri.lastIndexOf('/');
        int lastHashIndex = uri.lastIndexOf('#');

        // Determine the last index among '/' and '#'
        int lastIndex = Math.max(lastSlashIndex, lastHashIndex);

        if (lastIndex != -1) {
            // Extract and return the substring up to the last '/' or '#'
            return uri.substring(0, lastIndex + 1);
        }

        // If '/' or '#' is not found, return the whole URI
        return uri;
    }

    public Set<Property> getProperties(){
        return properties;
    }

    public Model getModel() {
        return model;
    }
}
