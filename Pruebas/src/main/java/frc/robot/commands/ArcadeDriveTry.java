// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ArcadeDriveTry extends Command {
  /** Creates a new ArcadeDriveTry. */

  DriveTrain driveTrain;
  Timer t;
  Boolean isFinished = false;

  public ArcadeDriveTry(DriveTrain d) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveTrain = d;
     t = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    t.start();
  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("works");
  

    if(t.get() < 30){
      driveTrain.onMotors();
    }
    else{
      driveTrain.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
