
package org.usfirst.frc.team3205.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3205.robot.commands.armUp;
import org.usfirst.frc.team3205.robot.commands.autoDrawbridgeGroup;
import org.usfirst.frc.team3205.robot.commands.autoDriveOver;
import org.usfirst.frc.team3205.robot.commands.autoPortcullisGroup;
import org.usfirst.frc.team3205.robot.commands.autoSallyPort;
import org.usfirst.frc.team3205.robot.commands.drivePastStuff;
import org.usfirst.frc.team3205.robot.commands.nothing;
//import org.usfirst.frc.team3205.robot.commands.cameraOne;
import org.usfirst.frc.team3205.robot.commands.resetArmEncoder;
import org.usfirst.frc.team3205.robot.commands.resetDriveTrainEncoders;
import org.usfirst.frc.team3205.robot.commands.shooterDown;
import org.usfirst.frc.team3205.robot.commands.tippyRampGroup;
import org.usfirst.frc.team3205.robot.subsystems.Arm;
import org.usfirst.frc.team3205.robot.subsystems.Drawbridge;
import org.usfirst.frc.team3205.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3205.robot.subsystems.Shooter;
//import org.usfirst.frc.team3205.robot.subsystems.Vision;




import org.usfirst.frc.team3205.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {


    Command autonomousCommand;
    SendableChooser chooser;
	public static OI oi;
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final Shooter shootey = new Shooter();
	public static Arm arm;
	public static final Drawbridge drawbridge = new Drawbridge();
	public static final Vision vision = new Vision();
	public static Encoder armEncoder;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	arm = new Arm();	
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addObject("Auto Drawbridge", new autoDrawbridgeGroup());
        chooser.addObject("Auto Portcullis", new autoPortcullisGroup());
        chooser.addObject("Auto Sally Port", new autoSallyPort());
        chooser.addObject("Tippy Ramp", new tippyRampGroup());
        chooser.addObject("Drive", new drivePastStuff());
        chooser.addObject("Nothing", new nothing());
        armEncoder = new Encoder(4,5, false, Encoder.EncodingType.k4X);
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putNumber("Arm Encoder", Robot.arm.getEncoder());
        SmartDashboard.putData("Reset arm Encoder", new resetArmEncoder());
        SmartDashboard.putData("Reset DriveTrain Encoders", new resetDriveTrainEncoders());
        updateSmartDashboard();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	armEncoder.reset();
    	updateSmartDashboard();
        autonomousCommand = (Command) chooser.getSelected();
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
      	SmartDashboard.putNumber("Arm Encoder in Auto", armEncoder.getRaw());
    	updateSmartDashboard();
    	//SmartDashboard.putNumber("Auto Arm Encoder", arm.getEncoder());
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	updateSmartDashboard();
        if (autonomousCommand != null) autonomousCommand.cancel();
//        if(OI.xbox.getRawAxis(3) < 0){
//        	armUp downArm = new armUp();
//        }
//        else if(OI.xbox.getRawAxis(3) > 0){
//        	shooterDown downShoot = new shooterDown();
//        }
        
//        try{
//	        if(shootey.isIntakeSet()){
//	        	Robot.oi.xbox.setRumble(RumbleType.kLeftRumble,1);
//	        	Robot.oi.xbox.setRumble(RumbleType.kRightRumble, 1);
//	        	Thread.sleep(5000);
//	        }
//	        Robot.oi.xbox.setRumble(RumbleType.kLeftRumble, 0);
//	        Robot.oi.xbox.setRumble(RumbleType.kRightRumble, 0);
//        }
//        catch(Exception e){
//        	
//        }
    }
        

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putNumber("Drawbridge Counter from Teleop", drawbridge.get());
//    	if(arm.isLowerLimitSet() && !drawbridge.isLowerLimitSet()){
//    		drawbridge.drawBridgeRetract();
//    	}
    	if(arm.isPortcullisLimitSet() && !arm.isLowerLimitSet()){
        	arm.moveDown();
        }
        updateSmartDashboard();
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void updateSmartDashboard(){
    	shootey.updateDashboard();
    	arm.updateSmartDashboard();
    	drivetrain.updateSmartDashboard();
    	drawbridge.updateDashboard();
    }

	
}


