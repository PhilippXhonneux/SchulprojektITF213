package Data;

import java.lang.reflect.Field;

/**
 * Basis Class for all Datastructur-objects
 *
 * @author Philipp Xhonneux
 * @version 1.5.0
 */
public abstract class Datastructur {

	private int intValue;
	private String stringValue;
	private double doubleValue;


	/**
	 * Gives back a String in CSV-format.
	 *
	 * @param separator the saparator used for the CSV-Format
	 * @return the string
	 */
	public String ToCSVString(String separator) {
		StringBuilder result = new StringBuilder();
		Class<?> clazz = getClass();

		try {
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true); // Zugriff auf private Felder erlauben
				result.append(field.get(this))
						.append(separator);
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
