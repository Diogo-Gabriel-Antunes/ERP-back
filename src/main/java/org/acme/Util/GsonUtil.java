package org.acme.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GsonUtil {

    public Gson parser = new GsonBuilder().registerTypeAdapter(LocalDate.class,new LocalDateAdapter()).create();

}
