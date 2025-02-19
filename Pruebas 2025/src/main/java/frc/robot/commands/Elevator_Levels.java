// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Coral_InTake;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Tej_Subs;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Elevator_Levels extends Command {
  /** Creates a new Elevator_Levels_Autonomus. */
  Elevator el;
  Coral_InTake ci;
  double finalp;
  double d;
  int mode;
  int mode2;
  boolean fin;
  public Elevator_Levels(Elevator el,Coral_InTake ci, double d, int mode,int mode2) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.el = el;
    this.ci = ci;
    this.d = d;
    this.mode = mode;
    this.mode2 = mode2;
    addRequirements(el);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finalp = el.getBasePosition() + d;
    fin = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (el.getPosition()<finalp) {
      el.elevatorMove(speed());
      //ci.move(0.2);
    }else{
      el.elevatorMove(-speed());
      //ci.move(0.2);
    }
    double difference = Math.abs(el.getPosition() - finalp);
    if (difference<0.2) {
      fin = true;
      el.setLightMode(mode);
    }else{
      el.setLightMode(mode2);
    }
    
  }
  public double speed(){
    return 0.05 + Math.min((0.6-0.05), Math.abs(el.getPosition() - finalp)/(0.8-0)*(0.6-0.05));
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    return fin;
  }
}
