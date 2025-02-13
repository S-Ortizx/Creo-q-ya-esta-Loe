// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;


public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  SparkMax motor_right_1;
  SparkMax motor_left_1;
  DifferentialDrive drive;

  
  public DriveTrain() {

    motor_right_1 = new SparkMax(RobotMap.motor_right_1,MotorType.kBrushless);
    motor_left_1 = new SparkMax(RobotMap.motor_left_1,MotorType.kBrushless);

    drive = new DifferentialDrive(motor_right_1,motor_left_1);

  }

  public void drive (double speed, double rotation){
    drive.arcadeDrive(speed, rotation);
    //System.out.println(motor_right_1.getEncoder().getPosition());
  }
  public double GetRightPosition(){
    return motor_right_1.getEncoder().getPosition();
  }
  
  public double GetLeftPosition(){
    return motor_left_1.getEncoder().getPosition();
  }

  public void turnLeft (){
    drive.arcadeDrive(0.6, 0);
  }

  public void turnRight (){
    drive.arcadeDrive(-0.4, 0);
  }
  public void stopTrain (){
    drive.arcadeDrive(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
