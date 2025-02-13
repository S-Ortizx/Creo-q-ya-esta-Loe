// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.opencv.core.Mat;

import com.fasterxml.jackson.core.type.TypeReference;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.resources.Navx;
import frc.robot.subsystems.DriveTrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Forward extends Command {
  /** Creates a new Forward. */
  DriveTrain dt;
  double startpodition;
  double finalposition;
  double difference;
  public Forward(DriveTrain d) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.dt = d;
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startpodition = dt.GetRightPosition();
    finalposition = startpodition + 63;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    dt.drive(0,-speed());
    System.out.println(speed());
  }
  public double speed(){
    return 0.005 + Math.min((0.6-0.005), Math.abs(dt.GetRightPosition() - finalposition)/(10-0)*(0.6-0.005));
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    difference = Math.abs(dt.GetRightPosition() - finalposition);
    if (difference<2) {
      return true;
    }
    return false;
  }
}
