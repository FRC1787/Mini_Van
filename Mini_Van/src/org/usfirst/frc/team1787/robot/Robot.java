package org.usfirst.frc.team1787.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.DriveTrain;


public class Robot extends TimedRobot {
	
	private DriveTrain driveTrain = DriveTrain.getInstance();
	
	// Joystick setup
	private final int RIGHT_JOYSTICK_ID = 0;
	private final int LEFT_JOYSTICK_ID = 1;
	private Joystick rightStick = new Joystick(RIGHT_JOYSTICK_ID);
	private Joystick leftStick = new Joystick(LEFT_JOYSTICK_ID);
	
	@Override
	public void robotInit() {
	
	}
	
	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {
		driveTrain.mecanumDriveAlg(1, 0, 0);
	}
	
	@Override
	public void teleopInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		
		//driveTrain.arcadeDrive(rightStick.getX(), rightStick.getY());
		//driveTrain.mecanumDriveTrig(rightStick.getX(), rightStick.getY(), rightStick.getZ());
		driveTrain.mecanumDriveAlg(-rightStick.getY(), rightStick.getX(), rightStick.getZ());
	}
	
	@Override
	public void testInit() {
		
	}
	
	@Override
	public void testPeriodic() {
		
	}
}
