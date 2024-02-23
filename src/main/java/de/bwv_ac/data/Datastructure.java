package de.bwv_ac.data;

import java.lang.reflect.Field;
import java.time.LocalTime;

/**
 * Basis Class for all Datastructur-objects
 *
 * @author Philipp Xhonneux
 * @version 2.0.2
 */
public abstract class Datastructure {

	/**
	 * Gives back a String in CSV-format.
	 *
	 * @param delimeter the saparator used for the CSV-Format
	 * @return the string
	 */
	public final String ToCSVString(String delimeter) {
		StringBuilder result = new StringBuilder();
		Class<?> clazz = getClass();

		try {
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true); // Zugriff auf private Felder erlauben
				result.append(field.get(this))
						.append(delimeter);
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

	/**
	 * Sets the all variables of the Object based on a CSV-Formated String
	 *
	 * @param csvString that contains the values
	 * @param delimeter for the CSV-Format
	 */
	public final void FromCSVStringToObject(String csvString, String delimeter) {
		String[] values = csvString.split(delimeter);
		Class<?> clazz = getClass();

		try {
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < values.length && i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true); // Zugriff auf private Felder erlauben
				String value = values[i].trim();
				if (field.getType() == int.class) {
					field.setInt(this, Integer.parseInt(value));
				} else if (field.getType() == double.class) {
					field.setDouble(this, Double.parseDouble(value));
				} else if (field.getType() == String.class) {
					field.set(this, value);
				} else if(field.getType() == LocalTime.class)
				{
					// Parameter der Methode (falls vorhanden)
					Class<?>[] parameterTypes = {String.class};

					// Methode finden
					Method method = this.getMethod("setTime", parameterTypes);

					method.invoke(this, value);
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
