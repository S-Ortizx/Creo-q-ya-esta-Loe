// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.resources.Navx;
import frc.robot.subsystems.DriveTrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Forward extends Command {
  /** Creates a new Forward. */
  DriveTrain dt;
  double finalp;
  public Forward(DriveTrain d) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.dt = d;
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finalp= dt.GetRightPosition()+30;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    dt.drive(0, -0.4);
    System.out.println("1111|1111111111"+dt.GetRightPosition());
    System.out.println("222322222222222"+finalp);
   // System.out.println(dt.GetRightPosition());
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
