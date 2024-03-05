package ont_checker;
import org.apache.jena.query.*;
import org.apache.jena.query.Query;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

public class RunQueries {
    // Query to check if the data is split based on a personal constraint that may be used in discrimination of the group
    // can you get rid of this dimension?
    final String discrim_query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT * " +
            "WHERE{" +
            "?s rdfs:label ?o." +
            "FILTER (CONTAINS(?o, 'sex') || CONTAINS(?o, 'unemploy') || CONTAINS(?o, 'age'))" +
            "}";

    // Query to check the specification of the involvement of vulnerable groups in the data
    // Be mindful about how this data is used and affects the vulnerable groups
    final String vulnerableppl_query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT * " +
            "WHERE{" +
            "?s rdfs:label ?o." +
            "FILTER (CONTAINS(?o, 'child') || CONTAINS(?o, 'disab') || CONTAINS(?o, 'criminal'))" +
            "}";

    final String qs_gender = "";

    public void print_hello(){
        System.out.println("In OntologyChecker class");
    }
    static boolean runQuery(String query_string, Model m){
        boolean found = false;
        System.out.println(query_string);
        Query q = QueryFactory.create(query_string);
        QueryExecution qexec = QueryExecutionFactory.create(q, m);
        try{
            ResultSet results = qexec.execSelect();
            while (results.hasNext()){
                found = true;
                QuerySolution solution = results.nextSolution();
                Resource name = solution.getResource("s");
                System.out.println(name);
            }
        } finally {
            qexec.close();
        }
        return found;
    }

    public void check(Model model){
        boolean result_discrimination = runQuery(discrim_query, model);
        boolean result_vulnerableppl = runQuery(vulnerableppl_query, model);
        System.out.println(result_discrimination);
        System.out.println(result_vulnerableppl);
    }
}
