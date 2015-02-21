package org.team2168.PID.sensors;

public interface PIDSensorInterface {
    /**
     *
     * @return the current rate of the sensor in nominal units of the sensor.
     *         Remember when using this method with a PID controller to ensure
     *         that the setPoints of the PID controller are in the same unit as
     *         returned by this function.
     */
    public double getRate();

    /**
     * Resets the rate of the sensor to zero and clears any
     * accumulators/counters to zero.
     */
    public void reset();

    /**
     *
     * @return the current Position of the sensor in nominal units of the
     *         sensor. Remember when using this method with a PID controller to
     *         ensure that the setPoints of the PID controller are in the same
     *         unit as returned by this function.
     */
    public double getPos();
}