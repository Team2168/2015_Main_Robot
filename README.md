FRC2015_Main_Robot
==================

This repository is for development of the 2015 Robot for the FIRST Robotics Competition.
It is written in Java and uses the Command Based Robot framework.

##Subsystems
The following sections identify the subsystems on the 2015 robot; what they do and with what sensors/actuators. This list will evolve as the season progresses. The bullets below each subsystem lists the relevant inputs/outputs it has.

###Drivetrain
The drivetrain has a total of 6 CIM motors, 3 on each side. Each is driven by a Talon motor controller. One PWM channel per controller.
Each side of the drive train has a single speed gear box. Attached directly to the output shaft of the gear box is a single 256 tick/rev encoder.
The transmission/wheel gear ratio is (XX:YY). Wheels are nominally 6" diameter.
* Six Talon motor controllers
* Two Encoders

A gyro will be available onboard the robot for rotational position measurements.
* One Gyro

###Linear Lift
The linear lift is a two stage assembly which can lift the gripper mechanism vertically.
The lift is driven by a worm gearbox driven by two mini-CIMs? Each motor is driven by one Victor motor controller.
There is a single 256 tick/rev encoder mounted to the output shaft of the gearbox.
A pnumatically actuated brake is attached to the gearbox to stop its rotation (and prevent vertical motion of the lift).
* Two Victor motor controllers
* One Encoder
* One double solenoid

###Gripper
The gripper assembly mounts onto the linear lift. It is what grips totes and canister.
There are a number of spring loaded fingers on the gripper which will engage on the lip of a tote. These fingers will passivley actuate (software doesn't control their position).
The gripper can be actuated pneumatically to open/close (horizontally).
* One double solenoid

###Intake
The intake consists of two arms with wheels at the end of them. Each wheel is driven by one RS775 motor. Each motor is connected to one Victor motor controller.
* Two Victor motor controllers

###Tote Pusher
The tote pusher is a flat plate at the front of the robot which can push a tote stack away from the robot.
Two pneumatic actuators move the assembly. This is controlled by a single double solenoid.
* One double solenoid

###Winch
[details to come]
