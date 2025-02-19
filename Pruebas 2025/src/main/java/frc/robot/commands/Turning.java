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
  double startangle;
  double finalangle;
  /** Creates a new Turning. */
  public Turning(DriveTrain d, Navx n) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.dt = d;
    this.n = n;
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startangle = n.getGyro();
    finalangle = finalangle();
    System.out.println("VVVVVVV"+n.getGyro()+"VVVVVVVV");
    System.out.println("$$$$$$$"+startangle+"$$$$$$$$");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      dt.drive(speed(),0);
    System.out.println("--->"+finalangle()+"<---");
    System.out.print("((("+n.getGyro()+")))");
  }
  public double speed(){
    return 0.05 + Math.min((0.6-0.05), Math.abs(n.getGyro() - finalangle)/(70-0)*(0.6-0.05));
  }
  public double finalangle(){
    if ((startangle + 180) < 360) {
      return (startangle + 180);
    }else{
      return ((startangle + 180) - 360);

    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double difference = Math.abs(n.getGyro() - finalangle);
    if(difference<2){
      return true;
    }
    return false;
  }
}
