package data_checker;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import java.util.HashSet;
import java.util.Set;

public class DataChecker {
    public void getProperties(Model model){

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
    }
}
