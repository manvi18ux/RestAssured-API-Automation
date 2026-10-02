package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {

    public static ResponseSpecification getResponseSpec() {

        return new ResponseSpecBuilder()
                .expectContentType("application/json")
                .build();
    }
}