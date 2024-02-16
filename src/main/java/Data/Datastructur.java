package Data;

import java.lang.reflect.Field;

/**
 * Basis Class for all Datastructur-objects
 *
 * @author Philipp Xhonneux
 * @version 2.0.1
 */
public abstract class Datastructur {

	/**
	 * Gives back a String in CSV-format.
	 *
	 * @param delimeter the saparator used for the CSV-Format
	 * @return the string
	 */
	public String ToCSVString(String delimeter) {
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
	public void FromCSVStringToObject(String csvString, String delimeter) {
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
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
