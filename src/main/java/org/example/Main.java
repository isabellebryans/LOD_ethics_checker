package org.example;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import ont_checker.OntologyChecker;
import utils.loadData;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        Model model = loadData.initAndLoadModelFromResource("czso-job-applicants-and-unemployment-rate-vocabulary.ttl", Lang.TURTLE);

        OntologyChecker checker = new OntologyChecker();
        checker.check(model);

        //RDFDataMgr.write(System.out, model, Lang.TTL);
        String queryString =
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "SELECT * " +
                "WHERE{" +
                        "?s rdfs:label ?o." +
                        "FILTER (CONTAINS(?o, 'sex') || CONTAINS(?o, 'unemploy'))" +
                        "}";
        //System.out.println(queryString);
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet results = qexec.execSelect();
            while (results.hasNext()){
                QuerySolution solution = results.nextSolution();
                Resource name = solution.getResource("s");
                //System.out.println(name);
            }
        } finally {
            qexec.close();
        }
    }
}