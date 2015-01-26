package org.team2168.utils;

import java.util.Vector;

/**
 * Contains basic functions that are used often.
 *
 * Some methods originally from 254's 2013 robot code:
 * https://github.com/Team254/FRC-2013
 * /blob/master/src/com/team254/lib/util/Util.java
 *
 * @author james@team2168.org (James Corcoran)
 * @author richard@team254.com (Richard Lin)
 * @author brandon.gonzalez.451@gmail.com (Brandon Gonzalez)
 */
public class Util {
	// Prevent this class from being instantiated.
	private Util() {
	}

	/**
	 * Find the minimum of two values.
	 * @param x
	 * @param y
	 * @return the smaller of the two values
	 */
	public static double min(double x, double y) {
		if (x > y) {
			return y;
		} else {
			return x;
		}
	}

	/**
	 * Find the maximum of two values.
	 * @param x
	 * @param y
	 * @return the larger of the two values
	 */
	public static double max(double x, double y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	/**
	 * Coerce a number within a defined range.
	 * @param value the number to constrain within the defined limit
	 * @param upperLimit number supplied value must not exceed
	 * @param lowerLimit number supplied value must not be less than
	 * @return the supplied value limited to within the defined range
	 */
	public static double limit(double value, double lowerLimit, double upperLimit) {
		return min(max(value, lowerLimit), upperLimit);
	}

	/**
	 * Returns the array of substrings obtained by dividing the given input
	 * string at each occurrence of the given delimiter.
	 */
	public static String[] split(String input, String delimiter) {
		Vector node = new Vector();
		int index = input.indexOf(delimiter);
		while (index >= 0) {
			node.addElement(input.substring(0, index));
			input = input.substring(index + delimiter.length());
			index = input.indexOf(delimiter);
		}
		node.addElement(input);

		String[] retString = new String[node.size()];
		for (int i = 0; i < node.size(); ++i) {
			retString[i] = (String) node.elementAt(i);
		}

		return retString;
	}

	/**
	 * Calculate the slope of a line given two points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return the slope
	 */
	public static double slope(double x1, double y1, double x2, double y2) {
		return (y2 - y1)/(x2 - x1);
	}

	/**
	 * Calculate the y intercept of the line given its slope and a point
	 *   on the line
	 * @param slope
	 * @param x
	 * @param y
	 * @return the y intercept of the line.
	 */
	public static double intercept(double slope, double x, double y) {
		return y - (slope * x);
	}
}