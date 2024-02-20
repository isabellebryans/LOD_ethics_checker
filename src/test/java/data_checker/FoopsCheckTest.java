package data_checker;

import ont_checker.FoopsCheck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FoopsCheckTest {
    String uri = "http://xmlns.com/foaf/0.1/";
    @Test
    void sendHttpFoopsTest() throws IOException {
        FoopsCheck check = new FoopsCheck(uri);
        System.out.println(check.getTitle());
        System.out.println(check.getChecks());


    }
}