package org.acme.Util;


import org.acme.models.DTO.DTO;
import org.acme.models.DTO.ProductDTO;
import org.acme.models.Model;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Field;

@ApplicationScoped
public class FieldUtil {

    public String updateStringToGetorSet(Field attribute){
        return attribute.getName().replaceFirst(attribute.getName().substring(0,1),attribute.getName().substring(0,1).toUpperCase());
    }

    public void updateFields(Model oldObject, DTO newObject){
        Field[] attributes = newObject.getClass().getDeclaredFields();
        for (Field attribute : attributes) {
            try {
                attribute.setAccessible(true);
                if(attribute.get(newObject) != null){
                    oldObject.getClass().getDeclaredMethod("set"+updateStringToGetorSet(attribute),attribute.getType()).invoke(oldObject,attribute.get(newObject));
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
}
