package controllers;

import entity.InternalError;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProcessErrorController {

    private void setResponseParameters (HttpServletResponse response){
        response.setStatus(500);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    protected static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    protected void processInternalErrors(HttpServletResponse response, InternalError internalError)
    throws IOException{
        processJsonMapper(response, internalError.getErrorMessage());
    }

    protected void processExceptions(HttpServletResponse response, Exception exception)
            throws IOException{
        processJsonMapper(response, exception.getMessage());
    }

    private void processJsonMapper(HttpServletResponse response, String message)
    throws IOException{
        setResponseParameters(response);
        String jsonInString = getObjectMapper().writeValueAsString(message);
        PrintWriter writer = response.getWriter();
        writer.print(jsonInString);
        writer.close();
    }



}
