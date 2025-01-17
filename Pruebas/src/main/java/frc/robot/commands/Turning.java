// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.print.attribute.standard.MediaSize.NA;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.resources.Navx;
import frc.robot.subsystems.DriveTrain;
import pabeles.concurrency.IntOperatorTask.Min;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Turning extends Command {
  DriveTrain dt;
  Navx n;
  double angle;
  double maxspeed;
  double minspeed;
  double maxangle;
  double minangle;
  /** Creates a new Turning. */
  public Turning(DriveTrain d, Navx n, double a, double maxspeed, double minspeed, double maxangle, double minangle) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.dt = d;
    this.n = n;
    this.angle = a;
    this.maxspeed = maxspeed;
    this.minspeed = minspeed;
    this.maxangle = maxangle;
    this.minangle = minangle;
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(n.getGyro()<angle){
      dt.drive((speed(n.getGyro(), angle)),0);;
    }else{
      dt.drive(-speed(n.getGyro(), angle),0);
    }
   // System.out.println("Gyro: "+ n.getGyro());
  }
  public double speed(double actual, double objetivo){
    return minspeed + Math.min((maxspeed-minspeed), Math.abs(actual - objetivo)/(maxangle-minangle)*(maxspeed-minspeed));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double a = Math.abs(n.getGyro() - angle);
    if(a<2){
      return true;
    }
    return false;
  }
}
