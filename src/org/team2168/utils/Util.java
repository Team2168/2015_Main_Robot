package org.team2168.utils;

import java.util.Vector;

/**
 * Contains basic functions that are used often.
 *
 * Initial revision from 254's 2013 robot code:
 * https://github.com/Team254/FRC-2013
 * /blob/master/src/com/team254/lib/util/Util.java
 *
 * @author richard@team254.com (Richard Lin)
 * @author brandon.gonzalez.451@gmail.com (Brandon Gonzalez)
 */
public class Util {
	// Prevent this class from being instantiated.
	private Util() {
	}

	/**
	 * Limits the given input to the given magnitude.
	 * @param v the value to limit
	 * @param limit a positive value defining the allowable magnitude of the
	 * @return the limited value
	 */
	public static double limit(double v, double limit) {
		return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
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