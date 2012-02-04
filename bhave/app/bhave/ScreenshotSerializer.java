package bhave;

import java.lang.reflect.Type;

import models.Screenshot;
import annotations.NoJsonExport;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ScreenshotSerializer implements JsonSerializer<Screenshot> {
	
	@Override
	public JsonElement serialize(Screenshot screenshot, Type type, JsonSerializationContext context) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new LocalExclusionStrategy()).create();
		
		JsonElement element = gson.toJsonTree(screenshot);
		return element;
	}

    public static class LocalExclusionStrategy implements ExclusionStrategy {
		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return clazz.getAnnotation(NoJsonExport.class) != null;
		}
	
		@Override
		public boolean shouldSkipField(FieldAttributes fieldAttributes) {
			return fieldAttributes.getAnnotation(NoJsonExport.class) != null;
		}
    }


}
