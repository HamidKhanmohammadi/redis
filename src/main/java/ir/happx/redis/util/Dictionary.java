package ir.happx.redis.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.MessageFormat;

/**
 * A class to load labels and messages from classpath:dictionary.json file
 * Any module that use this class must have a file named dictionary.json- in its classpath- whit two base values 'labels' and 'messages'
 * For example:
 * {
 * "labels": {
 * "appName": "NeoPay"
 * },
 * "messages": {
 * "welcome": "Welcome to NeoPay!"
 * }
 * }
 * <p>
 * Usage:
 * Dictionary.label("appName")
 */
public abstract class Dictionary {

    private static Logger logger = LoggerFactory.getLogger(Dictionary.class);
    private static JsonNode baseLabels;
    private static JsonNode baseValidations;
    private static JsonNode baseMessages;
    private static JsonNode baseExceptions;
    private static JsonNode labels;
    private static JsonNode validations;
    private static JsonNode messages;
    private static JsonNode exceptions;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("base-dictionary.json")) {
            JsonNode dictionary = mapper.readValue(inputStream, JsonNode.class);
            baseLabels = dictionary.get("labels");
            baseValidations = dictionary.get("validations");
            baseMessages = dictionary.get("messages");
            baseExceptions = dictionary.get("exceptions");
        } catch (Exception e) {
            logger.error("Message bundle load error.");
        }
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("dictionary.json")) {
            JsonNode dictionary = mapper.readValue(inputStream, JsonNode.class);
            labels = dictionary.get("labels");
            validations = dictionary.get("validations");
            messages = dictionary.get("messages");
            exceptions = dictionary.get("exceptions");
        } catch (Exception e) {
            logger.error("Message bundle load error.");
        }
    }

    /**
     * Reads value of bundles array by key
     *
     * @param bundles
     * @param key
     * @param isException
     * @param args
     * @return
     */
    private static String readValue(JsonNode[] bundles, String key, boolean isException, Object... args) {
        JsonNode node = null;
        for (JsonNode bundle : bundles) {
            node = bundle.get(key);
            if (node != null) {
                break;
            }
        }
        if (node != null) {
            String value = isException ? node.get("message").asText() : node.asText();
            if (args != null && args.length > 0) {
                return MessageFormat.format(value, args);
            }
            return value;
        }
        return "";
    }

    /**
     * Returns label string from labels.
     * If args are provided, respective string in Dictionary.json must be a template with indexed placeholder '{<arg_index>}'
     * for each argument. For example:
     * Dictionary.json:
     * {..
     * "key": "This is a test for {0}."
     * ...}
     * <p>
     * Code:
     * Dictionary.label("key", "neopay");
     *
     * @param key
     * @param args
     * @return String
     */
    public static String label(String key, Object... args) {
        return readValue(new JsonNode[]{labels, baseLabels}, key, false, args);
    }

    /**
     * Returns message string from validations.
     * If args are provided, respective string in Dictionary.json must be a template with indexed placeholder '{<arg_index>}'
     * for each argument. For example:
     * Dictionary.json:
     * {..
     * "key": "This is a test for {0}."
     * ...}
     * <p>
     * Code:
     * Dictionary.validation("key", "neopay");
     *
     * @param key
     * @param args
     * @return String
     */
    public static String validation(String key, Object... args) {
        return readValue(new JsonNode[]{validations, baseValidations}, key, false, args);
    }

    /**
     * Returns message string from messages.
     * If args are provided, respective string in Dictionary.json must be a template with indexed placeholder '{<arg_index>}
     * for each argument. For example:
     * Dictionary.json:
     * {...
     * "key": "This is a test for {0}."
     * ...}
     * <p>
     * Code:
     * Dictionary.message("key", "neopay");
     *
     * @param key
     * @param args
     * @return String
     */
    public static String message(String key, Object... args) {
        return readValue(new JsonNode[]{messages, baseMessages}, key, false, args);
    }

    /**
     * Returns neopay exception object from exceptions.
     * If args are provided, respective string in Dictionary.json must be a template with indexed placeholder '{<arg_index>}
     * for each argument. For example:
     * Dictionary.json:
     * {...
     * "key": "This is a test for {0}."
     * ...}
     * <p>
     * Code:
     * Dictionary.exception("key", "neopay");
     *
     * @param key
     * @param args
     * @return St
     * }ring
     */
    public static BanooExeption exception(String key, Object... args) {
        JsonNode[] bundles = new JsonNode[]{exceptions, baseExceptions};
        JsonNode exception = null;
        for (JsonNode bundle : bundles) {
            exception = bundle.get(key);
            if (exception != null) {
                break;
            }
        }
        String message = readValue(new JsonNode[]{exceptions, baseExceptions}, key, true, args);
        return new BanooExeption(exception.get("code").asText(),
          message, exception.get("httpStatus").asText());
    }
}