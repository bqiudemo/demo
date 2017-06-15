package math.service.rest.resources;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import math.service.MathService;
import math.util.rest.ResponseUtils;

/**
 * This JAX-RS resource provides configuration and management for math 
 * 
 */
@Path("math")
public class MathResource {

    @Context
    ServletContext servletContext;

    @Context
    HttpServletRequest request;
    
    static final Logger LOG = LoggerFactory.getLogger(MathResource.class);
    
    private MathService getMathService() {
        return ((MathService) servletContext.getAttribute(MathService.class.getName()));
    }

    
    /**
     * @apidescription Get the fibonacci number.
     *
     * @apiVersion 1.0.0
     * @apiGroup 
     * @apiName GetFibonacciNumber
     * @api {get} math/api/fibonacci/number
     * 
     * @apiSuccess {String[]} fibonacci List of fibonacci number
     * 
     * @apiExample Example usage:
     * curl -i http://hostname:8080/demo/api/math/fibonacci/300
     *
     * @apiSuccessExample {application/json} Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *          "fibonacci": [ "0", "0". "1" ...]
     *     }
     *
     * @apiErrorExample {application/json} Error-Response:
     *     HTTP/1.1 "Bad Request"
     *     {
     *       "status": "error",
     *       "message": "Negative input number"
     *     }
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/fibonacci/{number}")
    public Response getStagePetrinets(@PathParam("number") String number) {
    	
    	number = StringUtils.trimToNull(number);
    	long fibonacciNumber;
    	
    	try {
    		fibonacciNumber = Long.parseLong(number);
    	} catch (NumberFormatException nfe) {
    		String error = "NumberFormatException: " + nfe.getMessage();
    		return (ResponseUtils.getErrorResponse(Status.BAD_REQUEST, error));
        }
    	
    	String ret = getMathService().caculateFibonacci(fibonacciNumber);
    	
    	LOG.debug("caculateFibonacci number {} result {}", fibonacciNumber, ret);
    	
        if (ret == null) {
            return (ResponseUtils.getErrorResponse(Status.BAD_REQUEST, "Negative input number"));
        }
        
        Response resp = ResponseUtils.getOkResponse(ret);
        return resp;
    }
    
}
