// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Elevator_Levels_4 extends Command {
  /** Creates a new Elevator_Levels_Autonomus. */
  Elevator el;
  double finalp;
  double d;
  public Elevator_Levels_4(Elevator el, double d) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.el = el;
    this.d = d;
    addRequirements(el);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finalp = el.basePosition + d;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (el.getPosition()<finalp) {
      el.elevatorMove(speed());
    }else{
      el.elevatorMove(-speed());
    }
    
  }
  public double speed(){
    return 0.005 + Math.min((0.6-0.005), Math.abs(el.getPosition() - finalp)/(10-0)*(0.6-0.005));
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
     double difference = Math.abs(el.getPosition() - finalp);
    if (difference<10) {
      return true;
    }
    return false;
  }
}
