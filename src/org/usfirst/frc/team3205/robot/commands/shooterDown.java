package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class shooterDown extends Command {

    public shooterDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shootey);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shootey.moveDown();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.shootey.isLowerLimitSet()){
    		Robot.shootey.stopMoving();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//return Robot.shootey.isLowerLimitSet();
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shootey.stopMoving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shootey.stopMoving();
    }
}
