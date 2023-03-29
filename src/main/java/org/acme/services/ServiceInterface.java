package org.acme.services;

import javax.ws.rs.core.Response;

public interface ServiceInterface {

    public Response create(String json);

    public Response update(String uuid,String json);


}
