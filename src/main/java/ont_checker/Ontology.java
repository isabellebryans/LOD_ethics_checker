package ont_checker;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.DC_11;

import java.util.List;

public class Ontology {
    Model ontModel;
    String URI=null;
    // Metadata
    String description=null;
    String title=null;
    String date=null;

    int metaDataScore=0;

    // Check1 = check for the presence of vulnerable groups/people
    String[] Check1=null;
    // Check2 = check for the distinction of traits that could be discriminated against
    String[] Check2=null;
    // Check3 = check for sensitive topics
    String[] Check3=null;

    public Ontology(Model m){
        this.ontModel = m;
        //this.URI = uri;
        System.out.println("Ontology created");
        setMetaData();
    }

    public void testOntology(){
        Check1 = RunQueries.runCheck1(ontModel);
        Check2 = RunQueries.runCheck2(ontModel);
        Check3 = RunQueries.runCheck3(ontModel);
    }

    // Check metadata, description etc
    // Try to find keywords in dc:description where dc=http://purl.org/dc/elements/1.1/
    // Find the rdfs:labels of properties and classes
    // If they use rdfs:label, if not, flag
    //
    private void setMetaData(){
        String dc = "http://purl.org/dc/elements/1.1/";
        // Get title:
        NodeIterator titleIterator = ontModel.listObjectsOfProperty(DC_11.title);
        NodeIterator descriptionIterator = ontModel.listObjectsOfProperty(DC_11.description);
        NodeIterator dateIterator = ontModel.listObjectsOfProperty(DC_11.date);
        if (titleIterator.hasNext()) {
            RDFNode node = titleIterator.nextNode();
            if (node.isLiteral()) {
                Literal literal = node.asLiteral();
                // Now you can work with the literal
                System.out.println("Literal value: " + literal.getString());
                title = literal.getString();
            } else {
                // Handle non-literal nodes if needed
                System.out.println("Object of dc:title is not a Literal");
            }
        }
        if(descriptionIterator.hasNext()){
            RDFNode node = descriptionIterator.nextNode();
            if (node.isLiteral()) {
                Literal literal = node.asLiteral();
                // Now you can work with the literal
                System.out.println("Description: " + literal.getString());
                description = literal.getString();
            } else {
                // Handle non-literal nodes if needed
                System.out.println("Object of dc:description is not a Literal");
            }
        }

        if(dateIterator.hasNext()){
            RDFNode node = dateIterator.nextNode();
            if (node.isLiteral()) {
                Literal literal = node.asLiteral();
                // Now you can work with the literal
                System.out.println("Date: " + literal.getString());
                date = literal.getString();
            } else {
                // Handle non-literal nodes if needed
                System.out.println("Object of dc:date is not a Literal");
            }
        }
    }

}
