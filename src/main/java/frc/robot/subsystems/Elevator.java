// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */
  SparkMax elevatorMotor1;
  SparkMax elevatorMotro2;
  public double s;
  public Elevator() {
    elevatorMotor1 = new SparkMax(RobotMap.elevatorMotor1, MotorType.kBrushless);
    elevatorMotro2 = new SparkMax(RobotMap.elevatorMotor2, MotorType.kBrushless);
  }
  public void elevatorMove(double s){
    elevatorMotor1.set(s);
    elevatorMotro2.set(s);
  }
  public double getPosition(){
    return elevatorMotor1.getEncoder().getPosition();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
