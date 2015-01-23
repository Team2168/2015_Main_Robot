package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.lift.LiftWithJoystick;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The lift subsystem.
 */
public class Lift extends Subsystem {

    private static Lift instance = null;
    private Talon intakeMotor;
    private Encoder liftEncoder;
    private DoubleSolenoid liftBreak;
    double currentPosition;

    /**
     * A private constructor to prevent multiple instances of the subsystem from
     * being created.
     */
    private Lift() {
        intakeMotor = new Talon(RobotMap.LIFT_MOTOR);
        liftEncoder = new Encoder(RobotMap.LIFT_ENCODER_A,
                RobotMap.LIFT_ENCODER_B);
        liftBreak = new DoubleSolenoid(RobotMap.LIFT_DOUBLE_SOLENOID_FORWARD,
                RobotMap.LIFT_DOUBLE_SOLENOID_REVERSE);
    }

    /**
     * @return an instance of the subsystem
     */
    public static Lift getInstance() {
        if (instance == null) {
            instance = new Lift();
        }

        return instance;
    }

    /**
     * Set the default command for the subsystem.
     */
    public void initDefaultCommand() {
        setDefaultCommand(new LiftWithJoystick());
    }

    /**
     * Drive the lift in open loop mode.
     * 
     * @param speed
     *            value from -1.0 to 1.0, positive drives the lift up.
     */
    public void drive(double speed) {
        intakeMotor.set(speed);
    }

    /**
     * Get the lifts position along travel.
     * 
     * @return position in inches
     */
    public double getPosition() {
        return liftEncoder.getDistance();
    }

    /**
     * Command the lift to a specific position along travel.
     * 
     * @param position
     *            in inches.
     */
    public void setPosition(double position) {
        // TODO: establish minimum and maximum distances the lift can travel.
        // Put them in RobotMap.
        // TODO: If someone inputs values which are outside the min/max, coerce
        // them to be within range.

        double distanceToDrive = position - getPosition();
        double ABSvalue = Math.abs(distanceToDrive);

        if (distanceToDrive > 0) {
            setPositionDelta(ABSvalue, true);
        } else {
            setPositionDelta(ABSvalue, false);
        }
    }

    /**
     * Drive the lift to a position relative to where it currently is.
     * 
     * @param delta
     *            distance to travel in inches, positive is up
     * @param direction
     *            True for up, False for down
     */
    private void setPositionDelta(double delta, boolean direction) {
        if (delta > 1) {
            // TODO: Make separate commands to dis/engage the brake.
            // TODO: Then sequence the evolution in a CommandGroup.
            disableBreak();

            // TODO: determine a safe speed to operate this at.
            // TODO: Full speed (+/-1) is likely going to be (and overshoot your
            // destination).
            if (direction) {
                intakeMotor.set(1);
            } else {
                intakeMotor.set(-1);
            }
        } else {
            enableBreak();
        }

    }

    /**
     * Mark the current position of the lift as zero inches.
     */
    public void zeroPosition() {
        liftEncoder.reset();
    }

    /**
     * Identifies when the lift is at its highest position along travel.
     * 
     * @return true when at upper hard stop
     */
    public boolean upperHardStop() {
        // TODO: use sensor value
        return false;
    }

    /**
     * Identifies when the lift is at its lowest position along travel.
     * 
     * @return true when at the lower hard stop
     */
    public boolean lowerHardStop() {
        // TODO: use sensor value
        return false;
    }

    /**
     * Gets the sate of the current pneumatic break
     * 
     * @return True when break is enabled, False when break is disabled
     */
    public boolean isBreakEnabled() {
        if (liftBreak.get() == Value.kForward) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Enables the pneumatic break
     */
    public void enableBreak() {
        liftBreak.set(Value.kForward);
    }

    /**
     * Disables the pneumatic break
     */
    public void disableBreak() {
        liftBreak.set(Value.kReverse);
    }
}
