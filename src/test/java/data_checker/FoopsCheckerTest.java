package data_checker;

import ont_checker.FoopsChecker;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FoopsCheckerTest {
    String uri = "http://xmlns.com/foaf/0.1/";
    @Test
    void sendHttpFoopsTest() throws IOException {
        FoopsChecker check = new FoopsChecker(uri);
        System.out.println(check.getOntology_title());
        System.out.println(check.getChecks());

    }
}