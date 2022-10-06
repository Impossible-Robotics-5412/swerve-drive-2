package org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.common.hardware.Robot;

public class LiftExtendCommand extends SequentialCommandGroup {
    public LiftExtendCommand(Robot robot) {
        super(
                new LiftCommand(robot.lift, robot.lift.high_pos)
        );
    }
}
