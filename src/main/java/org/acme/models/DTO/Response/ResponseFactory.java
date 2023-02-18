package org.acme.models.DTO.Response;

import lombok.Getter;
import lombok.Setter;
import org.acme.exceptions.Validacao;
import org.acme.models.Model;
import org.apache.xpath.operations.Mod;

import java.util.List;

@Getter
@Setter
public class ResponseFactory {

    private List<Model> modelList;
    private List validacao;
    private Model model;

}
