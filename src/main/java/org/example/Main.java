package org.example;
import data_checker.DataSet;
import utils.DownloadFile;
import ont_checker.FoopsChecker;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import ont_checker.OntologyChecker;
import utils.LoadData;

import java.io.IOException;
import java.util.Set;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        Model model = LoadData.initAndLoadModelFromResource("czso-job-applicants-and-unemployment-rate-vocabulary.ttl", Lang.TURTLE);
        Model cszo_Unempl = LoadData.initAndLoadModelFromResource("czso-job-applicants-and-unemployment-rate.ttl", Lang.TURTLE);
        OntologyChecker checker = new OntologyChecker();
        checker.check(model);

        Model model1 = LoadData.initAndLoadModelFromResource("streetCrimeCamden.rdf", Lang.RDFXML);

        DataSet dataSet = new DataSet(model1);
        Set<String> ontologies = dataSet.getNamespaces();

        for (String ont : ontologies) {
            System.out.println(ont);
            FoopsChecker foopsChecker = new FoopsChecker(ont);
            System.out.println("Results of ontology:");
            System.out.println(foopsChecker.getOntology_title());
            System.out.println(foopsChecker.getOverall_score());
        }


    }
}