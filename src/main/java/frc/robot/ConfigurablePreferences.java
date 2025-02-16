package frc.robot;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Preferences;

public class ConfigurablePreferences {

    private enum TrapezoidalConstants {
        MAX_ACCELERATION_CONSTRAINT("MAX_ACCELERATION_CONSTRAINT",20),
        MAX_VELOCITY_CONSTRAINT("MAX_VELOCITY_CONSTRAINT",20),
        TARGET_VELOCITY("TARGET_VELOCITY",0),
        TARGET_POSITION("TARGET_POSITION",100);

        private double defaultValue;
        private String key;
        TrapezoidalConstants(String key, double defaultValue){
            this.key = key;
            this.defaultValue = defaultValue;
        }
    }

    private static NetworkTableInstance defaultInst = NetworkTableInstance.getDefault();
    private static NetworkTable m_table = defaultInst.getTable("Preferences");

    public static double getMaxAccelerationConstraint() {
        return getPreference(TrapezoidalConstants.MAX_ACCELERATION_CONSTRAINT.key, TrapezoidalConstants.MAX_ACCELERATION_CONSTRAINT.defaultValue);
    }

    public static double getMaxVelocityContraint() {
        return getPreference(TrapezoidalConstants.MAX_VELOCITY_CONSTRAINT.key, TrapezoidalConstants.MAX_VELOCITY_CONSTRAINT.defaultValue);
    }

    public static double getTargetPosition() {
        return getPreference(TrapezoidalConstants.TARGET_POSITION.key,TrapezoidalConstants.TARGET_POSITION.defaultValue);
    }

    public static double getTargetVelocity() {
        return getPreference(TrapezoidalConstants.TARGET_VELOCITY.key,TrapezoidalConstants.TARGET_VELOCITY.defaultValue);
    }

    private static double getPreference(String key, double value){
        NetworkTableEntry entry = m_table.getEntry(key);
        if(!entry.isPersistent()) {
            Preferences.initDouble(key, value);
        }
        return entry.getValue().getDouble();
    } 
}
