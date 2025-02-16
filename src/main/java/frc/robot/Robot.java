// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;

import edu.wpi.first.math.trajectory.TrapezoidProfile;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends LoggedRobot {
  private TrapezoidProfile profile = new TrapezoidProfile(new TrapezoidProfile.Constraints(ConfigurablePreferences.getMaxVelocityContraint(),ConfigurablePreferences.getMaxAccelerationConstraint()));
  private TrapezoidProfile.State goalState = new TrapezoidProfile.State();
  private TrapezoidProfile.State currentState = new TrapezoidProfile.State();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
    this.advKitInit();
  }
    
  private void advKitInit() {
    Logger.recordMetadata("ProjectName", "MyProject"); // Set a metadata value

    //This does not check for real or replay because it is only intended to be used in simulation
    if (isSimulation()) {
      Logger.addDataReceiver(new NT4Publisher());
    }

    Logger.start();  }
    
  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    goalState = new TrapezoidProfile.State(ConfigurablePreferences.getTargetPosition(),ConfigurablePreferences.getTargetVelocity());

    profile = new TrapezoidProfile(new TrapezoidProfile.Constraints(ConfigurablePreferences.getMaxVelocityContraint(),ConfigurablePreferences.getMaxAccelerationConstraint()));

    currentState = profile.calculate(0.02,currentState,goalState);
    Logger.recordOutput("Profile/TimeLeft", profile.timeLeftUntil(50));
    Logger.recordOutput("Profile/TotalTime", profile.totalTime());
    Logger.recordOutput("Profile/CurrentState/Position", currentState.position);
    Logger.recordOutput("Profile/CurrentState/Velocity", currentState.velocity);        
  }

  @Override
  public void teleopExit() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
