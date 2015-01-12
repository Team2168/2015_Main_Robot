FRC2015_Main_Robot
==================

This repository is for development of the 2015 Robot for the FIRST Robotics Competition.
It is written in Java and uses the Command Based Robot framework.

##Subsystems
The following sections identify the subsystems on the 2015 robot; what they do and with what sensors/actuators. This list will evolve as the season progresses. The bullets below each subsystem lists the relevant inputs/outputs it has.

###Drivetrain
The drivetrain has a total of 6 CIM motors, 3 on each side. Each is driven by a Talon motor controller. Assume that each set of three controllers is drive from a single PWM channel with a three way PWM Y cable.
Each side of the drive train has a single gear box. Attached directly to the output shaft of the gear box is a single 256 tick/rev encoder.
The transmission/wheel gear ratio is 27/24. Wheels are nominally 3" diameter.
* Two Talon motor controllers
* Two Encoders

A gyro will be available onboard the robot for rotational position measurements.
* One Gyro

###Intake
[details to come]

###Linear Lift
[details to come]

###Winch
[details to come]
