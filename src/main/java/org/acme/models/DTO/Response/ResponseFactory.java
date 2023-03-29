package org.acme.models.DTO.Response;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;

import java.util.List;

@Getter
@Setter
public class ResponseFactory {

    private List<Model> modelList;
    private List validacao;
    private Model model;

}
