// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveForever;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Forward;
import frc.robot.commands.ForwardThenTurn;
import frc.robot.commands.Algie_InOut;
import frc.robot.commands.Algie_InTake_Move;
import frc.robot.commands.Turning;
import frc.robot.commands.Elevator_Move;
import frc.robot.commands.Coral_Intake_Move;
import frc.robot.commands.Coral_Intake_Move_M;
import frc.robot.commands.Elevator_Levels_1;
import frc.robot.commands.Elevator_Levels_2;
import frc.robot.commands.Elevator_Levels_3;
import frc.robot.commands.Elevator_Levels_4;
import frc.robot.resources.Navx;
import frc.robot.subsystems.Coral_InTake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Algie_InTake;

import static edu.wpi.first.units.Units.Newton;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  DriveTrain driveTrain = new DriveTrain();
  Algie_InTake it = new Algie_InTake();
  Coral_InTake ci = new Coral_InTake();
  Navx navx = new Navx();
  Elevator elevator = new Elevator();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController xController = new CommandXboxController(RobotMap.XboxPort);
  private final CommandXboxController xController2 = new CommandXboxController(RobotMap.XboxPort2);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }
  public void updateNavx(){
    navx.run();
  }

  DriveTrain getDriveTrain(){
    return driveTrain;
  }
  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@links
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    elevator.setDefaultCommand(new Elevator_Move(elevator, xController,xController2));
    ///////////CONTROL 1/////////////////////////////////////////////////
    driveTrain.setDefaultCommand(new DriveForever(xController, driveTrain));
    xController.b().whileTrue(new Coral_Intake_Move(ci,RobotMap.coralspeed));
    xController.a().whileTrue(new Coral_Intake_Move(ci,-RobotMap.coralspeed));
    xController.rightBumper().whileTrue(new Algie_InTake_Move(it, true));
    xController.leftBumper().whileTrue(new Algie_InTake_Move(it, false));
    xController.y().whileTrue(new Algie_InOut(it, RobotMap.algiespeed));
    xController.x().whileTrue(new Algie_InOut(it, -RobotMap.algiespeed));
    ///////////CONTROL 2/////////////////////////////////////////////////
    ci.setDefaultCommand(new Coral_Intake_Move_M(ci,xController2));
    xController2.rightBumper().whileTrue(new Algie_InOut(it, -RobotMap.algiespeed));
    xController2.leftBumper().whileTrue(new Algie_InOut(it,RobotMap.algiespeed));
    xController2.povUp().whileTrue(new Algie_InTake_Move(it, true));
    xController2.povDown().whileTrue(new Algie_InTake_Move(it, false));
    xController2.a().whileTrue(new Elevator_Levels_2(elevator, RobotMap.level1));
    xController2.b().whileTrue(new Elevator_Levels_3(elevator, RobotMap.level2));
    xController2.y().whileTrue(new Elevator_Levels_4(elevator, RobotMap.level3));
    xController.x().whileTrue(new Elevator_Levels_1(elevator, 0));
    
  
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
  }//


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new ForwardThenTurn(driveTrain,navx);
  }
}