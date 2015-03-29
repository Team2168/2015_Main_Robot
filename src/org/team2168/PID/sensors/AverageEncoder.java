package org.team2168.PID.sensors;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

/**
 * This class extends the basic WPI encoder class. Its purpose is to provide a
 * smoother rate output by averaging the rate of N samplesIt Implements the
 * SpeedSensorInterface for use with our custom PID controller. Encoder with N
 * point averager Misspelling intentional.
 *
 * @author Kevin Harrilal, Team 2168 Aluminum Falcons
 *
 */
public class AverageEncoder extends Encoder implements PIDSensorInterface {
    private int averagorSize;
    private double[] averagorArray;
    private int arrayPos = 0; // Next array position to put values to be
    // averaged

    double timeNow;
    double oldTime;
    double countNow;
    double countBefore;
    double rate;

    private SpeedReturnType speedReturnType;
    private PositionReturnType posReturnType;

    int PPR;
    double distPerTick;

    /**
     * Constructor for end point average class
     *
     * @param n
     *            the size of end point average
     */
    public AverageEncoder(int channelA, int channelB, int PPR,
            double distPerTick, boolean reverseDirection,
            EncodingType encoderType, int averageN) {
        super(channelA, channelB, reverseDirection, encoderType);

        this.averagorSize = averageN;
        this.averagorArray = new double[averagorSize];
        this.timeNow = 0;
        this.oldTime = 0;
        this.countNow = 0;
        this.countBefore = 0;
        this.rate = 0;

        this.PPR = PPR;
        this.distPerTick = distPerTick;

        this.posReturnType = PositionReturnType.DEGREE;
        this.speedReturnType = SpeedReturnType.RPM;

        super.setDistancePerPulse(distPerTick);

    }

    public AverageEncoder(int channelA, int channelB, int PPR,
            double distPerTick, boolean reverseDirection,
            EncodingType encoderType, SpeedReturnType speedReturnType,
            PositionReturnType posReturnType, int averageN) {
        this(channelA, channelB, PPR, distPerTick, reverseDirection,
                encoderType, averageN);
        this.speedReturnType = speedReturnType;
        this.posReturnType = posReturnType;

    }

    

    public double getRawRate() {
    	
    	double rate = super.getRate(); //Inches per second
    	
    	switch (speedReturnType.value) {
        case SpeedReturnType.IPS_val:
            return rate;
       
        case SpeedReturnType.FPS_val:
            return (rate / 12); // feet per second
        
        case SpeedReturnType.RPM_val:
            return ((rate * 60) / (distPerTick*PPR)); // ticks per minute... rpm
           
        case SpeedReturnType.PERIOD_val:
            return (super.getPeriod()); // ticks per minute... rpm
     
        default:
            // should be unreachable
            putData(0);
            break;

        }

    	return super.getRate();
    }
    
    /**
     * returns (gets) Average of last n values sent, as name says.
     *
     * @return the Average
     */
    private double getAverage() {
        double sum = 0;

        for (int i = 0; i < averagorSize; i++)
            sum += averagorArray[i];

        return sum / averagorSize;
    }

    /**
     * puts data in to array to be averaged, hence the class name and method
     * name. Its like magic but cooler.
     *
     * @param value
     *            the value being inserted into the array to be averaged.
     */

    private void putData(double value) {

        averagorArray[arrayPos] = value;
        arrayPos++;

        if (arrayPos >= averagorSize) // Is equal or greater to averagorSize
            // because array is zero indexed. Rolls
            // over index position.
            arrayPos = 0;
    }

    //

    public double getRate() {
        // getRate
        timeNow = Timer.getFPGATimestamp();
        countNow = super.getDistance();
        rate = (countNow - countBefore) / (timeNow - oldTime); // inch per seconds
        oldTime = timeNow;
        countBefore = countNow;
        

        switch (speedReturnType.value) {
        case SpeedReturnType.IPS_val:
        	putData(rate);
            break;
        case SpeedReturnType.FPS_val:
            putData(rate / 12); // feet per second
            break;
        case SpeedReturnType.RPM_val:
            putData(( rate * 60 ) / (PPR * distPerTick)); // ticks per minute... rpm
            break;
        case SpeedReturnType.PERIOD_val:
            putData(super.getPeriod()); // ticks per minute... rpm
            break;
        default:
            // should be unreachable
            putData(0);
            break;

        }

        return getAverage(); // ticks per minute... rpm
        
    }

    public double getPos() {

        switch (posReturnType.value) {
        case PositionReturnType.TICKS_val:
            return get();
        case PositionReturnType.INCH_val:
            return super.getDistance();
        case PositionReturnType.DEGREE_val:
            return (double) (super.get()) / PPR * 360;
        case PositionReturnType.RADIANS_val:
            return (double) (super.get()) / PPR * (2 * Math.PI);
        case PositionReturnType.FEET_val:
            return super.getDistance()/12.0;
        default:
            // should be unreachable
            return 0;
        }
    }

    public static class SpeedReturnType {
        /**
         * The integer value representing this enumeration
         */
        static final int IPS_val = 0;
        static final int RPM_val = 1;
        static final int FPS_val = 2;
        static final int PERIOD_val = 3;
        final int value;
        /**
         * Count only the rising edge
         */
        public static final SpeedReturnType IPS = new SpeedReturnType(IPS_val);
        /**
         * Count both the rising and falling edge
         */
        public static final SpeedReturnType RPM = new SpeedReturnType(RPM_val);
        /**
         * Count rising and falling on both channels
         */
        public static final SpeedReturnType FPS = new SpeedReturnType(FPS_val);

        public static final SpeedReturnType PERIOD = new SpeedReturnType(
                PERIOD_val);

        private SpeedReturnType(int value) {
            this.value = value;
        }
    }

    public static class PositionReturnType {
        static final int TICKS_val = 0;
        static final int INCH_val = 1;
        static final int DEGREE_val = 2;
        static final int RADIANS_val = 3;
        static final int FEET_val = 4;
        public final int value;
        /**
         * Count only the rising edge
         */
        public static final PositionReturnType TICKS = new PositionReturnType(
                TICKS_val);
        /**
         * Count both the rising and falling edge
         */
        public static final PositionReturnType INCH = new PositionReturnType(
                INCH_val);
        /**
         * Count rising and falling on both channels
         */
        public static final PositionReturnType DEGREE = new PositionReturnType(
                DEGREE_val);

        public static final PositionReturnType RADIANS = new PositionReturnType(
                RADIANS_val);
        
        public static final PositionReturnType FEET = new PositionReturnType(
        		FEET_val);

        private PositionReturnType(int value) {
            this.value = value;
        }
    }

    public void setPosReturnType(PositionReturnType value) {
        this.posReturnType = value;
    }
}
