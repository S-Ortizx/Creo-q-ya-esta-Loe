// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotMap;
import frc.robot.resources.Navx;
import frc.robot.subsystems.DriveTrain;

public class TurningRight extends Command {
  /** Creates a new TurningRight. */
  DriveTrain turnDriveTrain;
  Navx navx;
  public TurningRight(DriveTrain driveTrain, Navx navx) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.turnDriveTrain = driveTrain;
    this.navx = navx;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turnDriveTrain.stopTrain();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean position = navx.getGyro() < RobotMap.turn_right_90;
    while (!position) {
      turnDriveTrain.turnRight();;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turnDriveTrain.stopTrain();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
