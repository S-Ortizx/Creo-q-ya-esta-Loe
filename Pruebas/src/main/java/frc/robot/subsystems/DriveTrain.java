// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  CANSparkMax motor_right_1;
  CANSparkMax motor_right_2;
  CANSparkMax motor_left_1;
  CANSparkMax motor_left_2;
  DifferentialDrive drive;

  
  public DriveTrain() {

    motor_right_1 = new CANSparkMax(RobotMap.motor_right_1,MotorType.kBrushless);
    motor_right_2 = new CANSparkMax(RobotMap.motor_right_2,MotorType.kBrushless);
    motor_left_1 = new CANSparkMax(RobotMap.motor_left_1,MotorType.kBrushless);
    motor_left_2 = new CANSparkMax(RobotMap.motor_left_2,MotorType.kBrushless);

    motor_right_2.follow(motor_right_1);
    motor_left_2.follow(motor_left_1);
   

    drive = new DifferentialDrive(motor_right_1,motor_left_1);

  }

  public void drive (double speed, double rotation){
    System.out.println("Drive train");
    drive.arcadeDrive(speed, rotation);
  }

  public void turnLeft (){
    motor_right_1.set(RobotMap.turnspeed);
    motor_left_1.set(-RobotMap.turnspeed);
  }

  public void turnRight (){
    motor_right_1.set(-RobotMap.turnspeed);
    motor_left_1.set(RobotMap.turnspeed);  
  }
  public void stopTrain (){
    motor_right_1.set(0);
    motor_left_1.set(0);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
