package ru.bryanin.dev.questionnairebackend.office.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class JsonValidator {

    public static boolean check(String json) throws IOException {
        InputStream schemaAsString = JsonValidator.class.getClassLoader().getResourceAsStream("questionnaires/schema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsString);
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(json);
        Set<ValidationMessage> errors = schema.validate(jsonNode);
        String errorsCombined = "";
        for (ValidationMessage error : errors) {
            errorsCombined += error.toString() + "\n";
        }
        if(errors.size() > 0) {
            throw new RuntimeException(" ERRORS " + errorsCombined);
        }
        System.out.println("errors.size() = " + errors.size());
        return true;
    }
}
