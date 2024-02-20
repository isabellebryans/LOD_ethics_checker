package org.example;
import data_checker.DataChecker;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.Lang;
import ont_checker.OntologyChecker;
import utils.loadData;

import java.io.IOException;
import java.io.StringWriter;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        Model model = loadData.initAndLoadModelFromResource("czso-job-applicants-and-unemployment-rate-vocabulary.ttl", Lang.TURTLE);
        Model cszo_Unempl = loadData.initAndLoadModelFromResource("czso-job-applicants-and-unemployment-rate.ttl", Lang.TURTLE);
        OntologyChecker checker = new OntologyChecker();
        checker.check(model);

        Model model1 = loadData.initAndLoadModelFromResource("streetCrimeCamden.rdf", Lang.RDFXML);
        DataChecker dataChecker = new DataChecker();
        dataChecker.getProperties(cszo_Unempl);
        //StringWriter out = new StringWriter();
        //RDFDataMgr.write(out, model1, Lang.TURTLE);
       // System.out.println(out.toString());
    }
}