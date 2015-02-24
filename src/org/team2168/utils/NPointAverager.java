package org.team2168.utils;

/**
 * An 'n'-point averager.
 *
 * @author Sultan Jilani
 *
 */
public class NPointAverager {

	private int averagerSize;
	private double[] averagerArray;
	private int arrayPos = 0;		//Next array position to put values to be averaged

	/**
	 * Constructor for end point average class
	 * @param n the size of end point average
	 */
	public NPointAverager(int n) {
		averagerSize = n;
		averagerArray = new double[averagerSize];
	}

	/**
	 * returns (gets) Average of last n values sent, as name says.
	 * @return the Average
	 */
	public double getAverage(){
		double holder = 0;

		for(int i = 0; i<averagerSize; i++){
			holder+=averagerArray[i];
		}

		return holder/averagerSize;
	}

	/**
	 * puts data in to array to be averaged, hence the class name and method
	 * name. Its like magic but cooler.
	 *
	 * @param value the value being inserted into the array to be averaged.
	 */
	public void putData(double value){
		averagerArray[arrayPos] = value;
		arrayPos++;

		if (arrayPos>=averagerSize) {		//Is equal or greater to
			arrayPos=0;						// averagorSize because array is
		}									// zero indexed. Rolls over index
		// position.
	}

}