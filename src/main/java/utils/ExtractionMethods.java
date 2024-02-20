package utils;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import java.util.HashSet;
import java.util.Set;

public class ExtractionMethods {
    public static Set<Property> extractProperties(Model model){
        // Collect unique properties
        Set<Property> properties = new HashSet<>();
        StmtIterator iter = model.listStatements();
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Property property = stmt.getPredicate();
            properties.add(property);
        }

        // Print the unique properties
        System.out.println("Unique Properties:");
        for (Property property : properties) {
            System.out.println(property.getURI());
        }
        return properties;
    }

    public static Set<String> extractNamespaces(Set<Property> properties){
        Set<String> namespaces = new HashSet<>();
        for (Property property : properties) {
            String namespace = extractNamespace(property.getURI());
            namespaces.add(namespace);
        }
        System.out.println("Namespaces:");
        for (String ns : namespaces) {
            System.out.println(ns);
        }
        return namespaces;
    }

    private static String extractNamespace(String uri) {
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
}
