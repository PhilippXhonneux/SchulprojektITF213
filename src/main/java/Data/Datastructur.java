package Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public abstract class Datastructur {

	private int intValue;
	private String stringValue;
	private double doubleValue;


	public String ToCSVString() {
		StringBuilder result = new StringBuilder();
		Class<?> clazz = getClass();

		try {
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true); // Zugriff auf private Felder erlauben
				result.append(field.get(this))
						.append(";");
			}
			// Entferne das letzte Komma
			if (result.length() > 0) {
				result.setLength(result.length() - 1);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return result.toString();
	}

}
