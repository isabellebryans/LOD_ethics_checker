package ont_checker;
import org.apache.jena.query.*;
import org.apache.jena.query.Query;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import java.io.File;
import java.util.ArrayList;

public class RunQueries {
    static final String[] Check1 = {"child", "criminal", "disab"};
    static final String[] Check2 = {"sex", "gender", "age", "ethnicity", "religion", "nationality"};
    static final String[] Check3 = {"crime", "education", "assault", "income"};

    public static String[] runCheck1(Model m){
        return runCheck(m, Check1);
    }
    public static String[] runCheck2(Model m){
        return runCheck(m, Check2);
    }

    public static String[] runCheck3(Model m){
        return runCheck(m, Check3);
    }

    private static String[] runCheck(Model m, String[] words){
        ArrayList<String> terms_found = new ArrayList<>();
        for (String word : words) {
            boolean found = false;
            String query_string = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "SELECT * " +
                    "WHERE{" +
                    "?s rdfs:label ?o." +
                    "FILTER (CONTAINS(?o, "+ word + ") )" +
                    "}";
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
                if (found){
                    terms_found.add(word);
                }
            } finally {
                qexec.close();
            }
        }

        return terms_found.toArray(new String[0]);
    }
}
