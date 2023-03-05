package org.acme.services;

import org.acme.models.DTO.DTO;
import org.acme.models.Model;

import javax.ws.rs.core.Response;
import java.awt.geom.RectangularShape;

public interface ServiceInterface {

    public Response create(String json);

    public Response update(String uuid,String json);


}
