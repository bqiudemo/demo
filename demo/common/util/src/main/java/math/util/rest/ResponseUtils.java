package math.util.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

public class ResponseUtils {

    public static final String OK_STATUS = "ok";
    public static final String ERROR_STATUS = "error";

    /**
     * Gets a JSON formatted string containing an optionally named array of String.
     * 
     * @param listName
     * @param values
     * @return a JSON formatted string containing an optionally named array of String
     */
    public static String getJsonArray(String listName, List<String> values) {

        listName = StringUtils.trimToNull(listName);
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        if (listName != null)
            jsonBuilder.append(" \"").append(listName).append("\":");

        jsonBuilder.append(" [ ");
        for (int i = 0; i < values.size(); i++) {
            if (i > 0)
                jsonBuilder.append(", ");

            jsonBuilder.append('"').append(values.get(i)).append('"');
        }

        jsonBuilder.append(" ] }");
        return (jsonBuilder.toString());
    }

    /**
     * Get a JSON formatted JAX-RS response with an HTTP status 200, a status string of "ok" and
     * the specified message.
     * 
     * @param message
     * @return a JAX-RS Response with JSON formatted content for a successful REST call
     */
    public static Response getOkResponse(String message) {
        return (getResponse(Status.OK, OK_STATUS, message));
    }

    /**
     * Get a JSON formatted JAX-RS response with an HTTP status 200, a status string of "ok" and
     * the specified message.
     * 
     * @param message
     * @return a JAX-RS Response with JSON formatted content for a successful REST call
     */
    public static Response getOkResponse(Status httpStatus, String message) {
        return (getResponse(httpStatus, OK_STATUS, message));
    }

    /**
     * Get a JSON formatted JAX-RS response with the specified HTTP status, a status string of "error" and
     * the specified message.
     * 
     * @param httpStatus
     * @param message
     * @return a JAX-RS Response with JSON formatted content for a REST call resulting in an error
     */
    public static Response getErrorResponse(Status httpStatus, String message) {
        return (getResponse(httpStatus, ERROR_STATUS, message));
    }

    /**
     * Get a JSON formatted JAX-RS response with the specified HTTP status, a status string of "error" and
     * the message from the exception.
     * 
     * @param httpStatus
     * @param e the Exception to get the message from
     * @return a JAX-RS Response with JSON formatted content for a REST call resulting in an error
     */
    public static Response getErrorResponse(Status httpStatus, Exception e) {
        return (getResponse(httpStatus, ERROR_STATUS, e.getMessage()));
    }

    /**
     * Get a JSON formatted JAX-RS response with the specified HTTP status, status string, and message.
     * 
     * @param httpStatus
     * @param status
     * @param message
     * @return a JAX-RS Response with JSON formatted content
     */
    public static Response getResponse(Status httpStatus, String status, String message) {
        return (Response.status(httpStatus).entity(message).type(MediaType.APPLICATION_JSON).build());
    }
}
