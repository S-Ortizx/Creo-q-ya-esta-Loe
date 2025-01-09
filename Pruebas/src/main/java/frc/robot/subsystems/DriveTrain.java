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
  CANSparkMax motor_left_1;
  DifferentialDrive drive;

  
  public DriveTrain() {

    motor_right_1 = new CANSparkMax(RobotMap.motor_right_1,MotorType.kBrushless);
    motor_left_1 = new CANSparkMax(RobotMap.motor_left_1,MotorType.kBrushless);

   

    drive = new DifferentialDrive(motor_right_1,motor_left_1);

  }

  public void drive (double speed, double rotation){
    drive.arcadeDrive(speed, rotation);
  }

  public void turnLeft (){
    drive.arcadeDrive(0, -0.5);
  }

  public void turnRight (){
    drive.arcadeDrive(0, 0.5);
  }
  public void stopTrain (){
    drive.arcadeDrive(0, 0);
  }
  public void aceleration (){

    drive.arcadeDrive(0, 0);
  }

  public void onMotors(){
    motor_right_1.set(0.3);
    motor_left_1.set(0.3);
  }

  public void stop(){
    motor_right_1.set(0);
    motor_left_1.set(0);
  }

  public void separate(double motor_right, double motor_left){

    motor_right_1.set(motor_right);
    motor_left_1.set(motor_left);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
