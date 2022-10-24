package org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands.auto;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.common.hardware.Robot;

public class CycleCommand extends SequentialCommandGroup {
    public CycleCommand(Robot robot) {
        super(
                //extend intake to pick up
                // TODO replace with TBD IntakeCommand
                new InstantCommand(() -> robot.intake.setDVA(400, 750, 2500)),
                new InstantCommand(() -> robot.intake.resetTimer()),
                new InstantCommand(() -> robot.intake.openClaw()),
                new InstantCommand(() -> robot.intake.extendForebar()),
                new InstantCommand(() -> robot.intake.intakeTurret()),

                //extend slides to deposit
                // TODO replace with new LiftCommand
                new InstantCommand(() -> robot.lift.setDVA(525, 1000, 7500)),
                new InstantCommand(() -> robot.lift.resetTimer()),

                //wait until ready to intake
                new WaitUntilCommand(() -> robot.intake.getPos() > 380),

                //intake
                new WaitCommand(200),
                new InstantCommand(() -> robot.intake.closeClaw()),
                new WaitCommand(200),
                new InstantCommand(() -> robot.intake.closeForebar()),
                new InstantCommand(() -> robot.intake.depositTurret()),
                // TODO replace with TBD IntakeCommand
                new InstantCommand(() -> robot.intake.setDVA(-400, -750, -2500)),
                new InstantCommand(() -> robot.intake.resetTimer()),
                // TODO replace with new LiftCommand
                new InstantCommand(() -> robot.lift.setDVA(-525, -1000, -7500)),
                new InstantCommand(() -> robot.lift.resetTimer()),

                //transfer
                new WaitUntilCommand(() -> robot.intake.getPos() < 40 && robot.lift.getPos() < 40),
                new InstantCommand(() -> robot.intake.openClaw()),
                new WaitCommand(1000)
        );
    }
}
