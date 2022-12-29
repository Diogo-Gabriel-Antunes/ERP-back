package org.acme.Util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime();
    }
}
