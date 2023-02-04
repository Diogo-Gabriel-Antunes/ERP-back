package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class Service {
    @Inject
    protected EntityManager em;
    protected Gson gson = new GsonUtil().parser;
    protected FieldUtil fieldUtil = new FieldUtil();
    protected DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
}
