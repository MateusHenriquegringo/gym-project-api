package edu.mateus.Gym.Users.tools;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class DecimalJsonSerializer extends JsonSerializer<Double> {
	@Override
	public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeNumber(String.format("%2f", value));
	}
}
