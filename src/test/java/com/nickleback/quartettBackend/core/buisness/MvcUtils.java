package com.nickleback.quartettBackend.core.buisness;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.List;

public class MvcUtils {

    public static <T> List<T> convertList(MvcResult result, Class<T> clazz) throws IOException {
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(contentAsString, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static <T> T convertObject(MvcResult result, Class<T> responseClass) throws IOException {
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(contentAsString, responseClass);
    }

//    public static void checkReturnedErrors(MvcResult result, String... messageArray) throws IOException {
//        List<String> messages = Arrays.asList(messageArray);
//        JsonValidationErrors jsonValidationErrors = MvcUtils.convertObject(result, JsonValidationErrors.class);
//
//        assertEquals(messages.size(), jsonValidationErrors.getErrors().size());
//
//        for(ValidationError validationError : jsonValidationErrors.getErrors()){
//            List<String> locatedMessages = messages.stream()
//                    .filter(message -> validationError.getValue().equals(message))
//                    .collect(Collectors.toList());
//
//            Assert.assertFalse(locatedMessages.isEmpty());
//            Assert.assertFalse(locatedMessages.size() > 1);
//        }
//    }

}
