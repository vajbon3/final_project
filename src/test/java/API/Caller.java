package API;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class Caller {
    public String baseURI;

    public Caller(String baseURI) {
        this.baseURI = baseURI;
        RestAssured.baseURI = baseURI;
    }

    public Response get(String url) {
        RequestSpecification request = RestAssured.given();

        return request.get(url);
    }

    public Response post(String url,
                         HashMap<String,String> headers,
                         String body) {
        RequestSpecification request = RestAssured.given();

        // set headers
        for(HashMap.Entry<String,String> header : headers.entrySet()) {
            request.header(header.getKey(),header.getValue());
        }

        // set payload to send
        request.body(body);

        // response
        return request.post(url);
    }

    Jackson2Mapper getMapper() {
        return new Jackson2Mapper((type, s) -> {
            ObjectMapper om = new ObjectMapper().findAndRegisterModules();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        });
    }
}
