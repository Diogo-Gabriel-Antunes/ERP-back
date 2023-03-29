package org.acme.Providers.filters;

import io.vertx.core.http.HttpServerRequest;
import org.acme.Util.JsonUtil;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Provider
public class JsonFilter implements ContainerRequestFilter {
    StringBuilder builder;
    InputStream entityStream;
    InputStreamReader inputStreamReader;
    BufferedReader reader;
    @Context
    UriInfo info;

    @Context
    HttpServerRequest request;
    @Override
    public void filter(ContainerRequestContext context) throws IOException {
//        if(context.getMethod().equalsIgnoreCase("POST") || context.getMethod().equalsIgnoreCase("PUT")){
//            entityStream = context.getEntityStream();
//            inputStreamReader = new InputStreamReader(entityStream,"UTF-8");
//            reader = new BufferedReader(inputStreamReader);
//            builder = new StringBuilder();
//            String line;
//            while((line = reader.readLine()) != null){
//                builder.append(line);
//            }
//        }
//        if(builder != null && entityStream != null && inputStreamReader != null && reader != null){
//            entityStream.close();
//            inputStreamReader.close();
//            reader.close();
//            String json = JsonUtil.preValidateFilter(builder.toString());
//            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
//            context.setEntityStream(new ByteArrayInputStream(bytes));
//        }

    }
}
