package org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.purepursuit.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.common.purepursuit.geometry.Pose;
import org.firstinspires.ftc.teamcode.common.purepursuit.localizer.Localizer;
import org.firstinspires.ftc.teamcode.common.purepursuit.path.PurePursuitConfig;
import org.firstinspires.ftc.teamcode.common.purepursuit.path.PurePursuitController;

public class PositionCommand extends CommandBase {
    Drivetrain drivetrain;
    Localizer localizer;
    Pose targetPose;
    ElapsedTime timer;

    private double ms;

    public PositionCommand(Drivetrain drivetrain, Localizer localizer, Pose targetPose, double ms) {
        this.drivetrain = drivetrain;
        this.localizer = localizer;
        this.targetPose = targetPose;
        this.ms = ms;
    }

    @Override
    public void execute() {
        if(timer == null){
            timer = new ElapsedTime();
        }
        drivetrain.set(PurePursuitController.goToPosition(localizer.getPos(), targetPose));
    }

    @Override
    public boolean isFinished() {
        Pose error = targetPose.subtract(localizer.getPos());

        if (((Math.hypot(error.x, error.y) < PurePursuitConfig.ALLOWED_TRANSLATIONAL_ERROR) && (Math.abs(error.heading) < PurePursuitConfig.ALLOWED_HEADING_ERROR)) || (timer.milliseconds() > ms)) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.set(new Pose());
        System.out.println("false");
    }
}
