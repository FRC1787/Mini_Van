package subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;

public class DriveTrain extends TimedRobot
{
	
	private int LEFT_FRONT_TALON_ID = 1;
	private int LEFT_REAR_TALON_ID = 2;
	private int RIGHT_FRONT_TALON_ID = 3;
	private int RIGHT_REAR_TALON_ID = 4;
	
	private WPI_TalonSRX leftFront = new WPI_TalonSRX(LEFT_FRONT_TALON_ID);
	private WPI_TalonSRX leftRear = new WPI_TalonSRX(LEFT_REAR_TALON_ID);
	private WPI_TalonSRX rightFront = new WPI_TalonSRX(RIGHT_FRONT_TALON_ID);
	private WPI_TalonSRX rightRear = new WPI_TalonSRX(RIGHT_REAR_TALON_ID);
	
	double r, robotAngle;
	double speedLF, speedLR, speedRF, speedRR;
	
	public DriveTrain() 
	{
		leftFront.setInverted(false);
		leftRear.setInverted(false);
		rightFront.setInverted(false);
		rightRear.setInverted(false);
		
		leftFront.configVoltageCompSaturation(12, 10);
		leftRear.configVoltageCompSaturation(12, 10);
		rightFront.configVoltageCompSaturation(12, 10);
		rightRear.configVoltageCompSaturation(12, 10);
		
		leftFront.enableVoltageCompensation(true);
		leftRear.enableVoltageCompensation(true);
		rightFront.enableVoltageCompensation(true);
		rightRear.enableVoltageCompensation(true);
		
		leftFront.setNeutralMode(NeutralMode.Brake);
		leftRear.setNeutralMode(NeutralMode.Brake);
		rightFront.setNeutralMode(NeutralMode.Brake);
		rightRear.setNeutralMode(NeutralMode.Brake);
	}
	
	private static final DriveTrain instance = new DriveTrain();
	
	private double truncateMotorOutput(double motorOutput) {
		if (motorOutput > 1) {
			return 1;
		} else if (motorOutput < -1) {
			return -1;
		} else {
			return motorOutput;
		}
	}
	
	public void arcadeDrive(double xAxis, double yAxis) {
		yAxis = yAxis * Math.abs(yAxis);
		xAxis = xAxis * Math.abs(xAxis);

		// Simons algorithm
		double leftMotorOutput = yAxis + xAxis;
		double rightMotorOutput = yAxis - xAxis;

		leftMotorOutput = truncateMotorOutput(leftMotorOutput);
		rightMotorOutput = truncateMotorOutput(rightMotorOutput);

		leftFront.set(leftMotorOutput);
		leftRear.set(leftMotorOutput);
		rightFront.set(rightMotorOutput);
		rightRear.set(rightMotorOutput);

	}
	
	public void mecanumDriveTrig(double speedAxis, double strafeAxis, double turnAxis) {
		r = Math.hypot(speedAxis, turnAxis);
		robotAngle = Math.atan2(turnAxis, speedAxis) - (Math.PI / 4);
		
		speedLF = r * Math.cos(robotAngle) + turnAxis;
		speedLR = r * Math.sin(robotAngle) - turnAxis;
		speedRF = r * Math.sin(robotAngle) + turnAxis;
		speedRR = r * Math.cos(robotAngle) - turnAxis;
		
		leftFront.set(truncateMotorOutput(speedLF));
		leftRear.set(truncateMotorOutput(speedLR));
		rightFront.set(truncateMotorOutput(speedRF));
		rightRear.set(truncateMotorOutput(speedRR));
	}
	
	
	public void mecanumDriveTrig2(double speedAxis, double strafeAxis, double turnAxis) {
		//Maybe the better drive algorithm
		
		///r = Math.hypot(speedAxis, turnAxis);
		robotAngle = turnAxis - Math.PI / 4;
		
		speedLF = speedAxis * Math.sin(robotAngle) + strafeAxis;
		speedLR = speedAxis * Math.cos(robotAngle) + strafeAxis;
		speedRF = speedAxis * Math.cos(robotAngle) - strafeAxis;
		speedRR = speedAxis * Math.sin(robotAngle) - strafeAxis;
		
		leftFront.set(truncateMotorOutput(speedLF));
		leftRear.set(truncateMotorOutput(speedLR));
		rightFront.set(truncateMotorOutput(speedRF));
		rightRear.set(truncateMotorOutput(speedRR));
	}
	
	public void mecanumDriveAlg(double forward, double strafe, double rotate) {
		speedLF = truncateMotorOutput(forward + strafe + rotate);
		speedLR = truncateMotorOutput(forward - strafe + rotate);
		speedRF = truncateMotorOutput(-forward + strafe + rotate);
		speedRR = truncateMotorOutput(-forward - strafe + rotate);
		
		leftFront.set(speedLF);
		rightFront.set(speedRF);
		leftRear.set(speedLR);
		rightRear.set(speedRR);
	}
	
	
	public void mecanumDriveWPI() {
		
	}
	
	public static DriveTrain getInstance() {
		return instance;
	}

}
