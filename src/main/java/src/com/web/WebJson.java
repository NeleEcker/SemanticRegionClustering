package src.com.web;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;
 
@Path("/download")
public class WebJson
{
    @POST
    @Path("/json")
    public Response downloadPdfFile(@Context final UriInfo info, @DefaultValue("8.476682") @QueryParam("latitude") String latitude, @DefaultValue("49.483752") @QueryParam("longitude") String longitude, @DefaultValue("simple") @QueryParam("algorithm") String algorithm)
    {

        StreamingOutput fileStream =  new StreamingOutput()
        {
            public void write(java.io.OutputStream output) throws IOException, WebApplicationException
            {
                try
                {

                	System.out.println("Request received for area (" + latitude + ", " + longitude+ ").");
                	
                	
                	FrontProcessor processor = new FrontProcessor();
                	processor.process(latitude, longitude, algorithm);
                	File tmpFile = GeoJsonFormatter.format(processor.getInstances(), processor.getClusters());
                	
                	
                	
            		System.out.println("-> Returning results of size "+ ((int) (tmpFile.length()  / 1024)) +"KB ...");
            		;
                    java.nio.file.Path path = Paths.get(tmpFile.getAbsolutePath());
                    byte[] data = Files.readAllBytes(path);
                    tmpFile.delete();
                    output.write(data);
                    output.flush();
                }
                catch (Exception e)
                {
                	e.printStackTrace();
                    //throw new WebApplicationException("File Not Found !!");
                }
            }
        };
        return Response
                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition","attachment; filename = myfile.pdf")
                .build();
    }
}