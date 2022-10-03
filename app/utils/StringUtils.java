package utils;

public class StringUtils {
	public static String sanitizeLicensePlate(String licensePlate) {
		licensePlate = licensePlate.replaceAll("-", "");
		licensePlate = licensePlate.replaceAll(" ", "");
		return licensePlate.trim();
	}
}
