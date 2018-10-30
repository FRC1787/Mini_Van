package subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrain {
	
	private int LEFT_FRONT_TALON_ID = 1;
	private int LEFT_REAR_TALON_ID = 2;
	private int RIGHT_FRONT_TALON_ID = 3;
	private int RIGHT_REAR_TALON_ID = 4;
	
	private WPI_TalonSRX leftFront = new WPI_TalonSRX(LEFT_FRONT_TALON_ID);
	private WPI_TalonSRX leftRear = new WPI_TalonSRX(LEFT_REAR_TALON_ID);
	private WPI_TalonSRX rightFront = new WPI_TalonSRX(RIGHT_FRONT_TALON_ID);
	private WPI_TalonSRX rightRear = new WPI_TalonSRX(RIGHT_REAR_TALON_ID);
	
	public DriveTrain() {
		leftFront.setInverted(true);
		leftRear.setInverted(false);
		rightFront.setInverted(false);
		rightRear.setInverted(false);
		
		
		
	}
	
	private static final DriveTrain instance = new DriveTrain();
	
	public void arcadeDrive(double yAxis, double xAxis) {
		
	}
	
	public void motorControl(double yAxis) {
		leftFront.set(yAxis);
	}
	
	public static DriveTrain getInstance() {
		return instance;
	}

}
