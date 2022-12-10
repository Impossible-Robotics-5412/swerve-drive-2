package org.firstinspires.ftc.teamcode.opmode.teleop;

import static java.lang.Math.PI;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.outoftheboxrobotics.photoncore.PhotonCore;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands.LiftCommandGeneric;
import org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands.subsystem.ClawCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands.IntakeRetractCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands.subsystem.LiftCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands.CycleCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands.subsystem.FourbarCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands.subsystem.LiftRetractCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.command.subsystemcommands.subsystem.TurretCommand;
import org.firstinspires.ftc.teamcode.common.hardware.FileInterface;
import org.firstinspires.ftc.teamcode.common.hardware.Robot;
import org.firstinspires.ftc.teamcode.common.purepursuit.drive.swerve.SwerveDrivetrain;
import org.firstinspires.ftc.teamcode.common.purepursuit.geometry.Point;
import org.firstinspires.ftc.teamcode.common.purepursuit.geometry.Pose;
import org.firstinspires.ftc.teamcode.common.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.common.subsystem.LiftSubsystem;

@Config
@TeleOp(name = "OpMode👌👌😍🎶🎶😎")
public class OpMode extends CommandOpMode {
    private Robot robot;

    private ElapsedTime timer;
    private double loopTime = 0;

    boolean xLock = false;
    boolean pDRS = false;

    boolean pDLB = false;
    boolean pDRB = false;
    boolean pDDL = false;
    boolean pDRT = false;
    boolean pDLT = false;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();
        robot = new Robot(hardwareMap, false);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//        robot.intake.extension.set(-0.4);
//        robot.lift.lift.set(-0.3);
         FileInterface.read(FileInterface.INTAKE);
         FileInterface.read(FileInterface.LIFT);
         FileInterface.read(FileInterface.IMU);
//        robot.intake.setFourbar(robot.intake.fourbar_transition);
        PhotonCore.CONTROL_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        PhotonCore.experimental.setMaximumParallelCommands(8);
        PhotonCore.enable();
//        IntakeSubsystem.fourbar_extended = 0.2;
    }

    @Override
    public void run() {
        if (timer == null) {
            timer = new ElapsedTime();
            robot.reset();
        }

        robot.read();
        double speedMultiplier = 1 - 0.75 * gamepad1.right_trigger;
        // Drivetrain
        Pose drive = new Pose(
                new Point((Math.pow(Math.abs(gamepad1.left_stick_y) > 0.02 ? gamepad1.left_stick_y : 0, 3) * speedMultiplier),
                        (Math.pow(-(Math.abs(gamepad1.left_stick_x) > 0.02 ? gamepad1.left_stick_x : 0), 3)) * speedMultiplier).rotate(robot.getAngle()- SwerveDrivetrain.imuOff),
                (Math.pow(-gamepad1.right_stick_x, 3)) * speedMultiplier
        );

        if (gamepad1.left_bumper) {
//            SwerveDrivetrain.imuOff = robot.getAngle();
////            robot.intake.extension.resetEncoder();
//            robot.lift.lift.resetEncoder();
//
        }

        // Gamepad2
//        if (gamepad2.dpad_up) {
//            robot.lift.setSlideFactor(1);
//        } else if (gamepad2.dpad_down) {
//            robot.lift.setSlideFactor(-1);
//        }

//        boolean dDL = gamepad2.dpad_left;
//        if (dDL && !pDDL) schedule(new CycleCommand(robot));
//        pDDL = dDL;
//
//        boolean dLT = (gamepad2.left_trigger > 0.3);
//        boolean dRT = (gamepad2.right_trigger > 0.3);
//        if (dLT && ! pDLT) {
//            schedule(new ClawCommand(robot, IntakeSubsystem.ClawState.OPEN));
//        } else if (dRT && !pDRT) {
//            schedule(new ClawCommand(robot, IntakeSubsystem.ClawState.CLOSED));
//        }
//
//        double gamepad2_left_stick_y = gamepad2.left_stick_y;
//        if (Math.abs(gamepad2_left_stick_y) > 0.15) {
//            robot.intake.setFourbarFactor(Math.pow(gamepad2_left_stick_y, 3));
//        }
//
//        double gamepad2_left_stick_x = gamepad2.left_stick_x;
//        if (Math.abs(gamepad2_left_stick_x) > 0.01) {
//            robot.intake.setSlideFactor(Math.pow(gamepad2_left_stick_x, 3));
//        }
//
//        double gamepad2_right_stick = gamepad2.right_stick_x;
//        if (Math.abs(gamepad2_right_stick) > 0.15) {
//            robot.intake.setTurretFactor(Math.pow(gamepad2_right_stick, 3));
//        }
//
//        boolean dLB = gamepad2.left_bumper;
//        boolean dRB = gamepad2.right_bumper;
//        if (dLB && !pDLB) {
//            schedule(new TurretCommand(robot, IntakeSubsystem.TurretState.INTAKE),
//                     new FourbarCommand(robot, IntakeSubsystem.FourbarState.INTAKE),
//                     new ClawCommand(robot, IntakeSubsystem.ClawState.OPEN));
//        } else if (dRB && !pDRB) {
//            schedule(new IntakeRetractCommand(robot));
//        }
//        pDRB = dRB;
//        pDLB = dLB;

        // TODO: Add latch stuff here for LiftCommand
//        if (gamepad2.a) {
//            schedule(new LiftCommandGeneric(robot, LiftSubsystem.LiftState.LOW));
//        } else if (gamepad2.x) {
//            schedule(new LiftCommandGeneric(robot, LiftSubsystem.LiftState.MIDDLE));
//        } else if (gamepad2.y) {
//            schedule(new LiftCommandGeneric(robot, LiftSubsystem.LiftState.HIGH));
//        } else if (gamepad2.b) {
//            schedule(new LiftRetractCommand(robot));
////                    new InstantCommand(() -> robot.lift.newProfile(-10, 3500, 7000)),
////                    new InstantCommand(() -> robot.lift.update(LiftSubsystem.LiftState.RETRACTED)));
//        }

        robot.drivetrain.set(drive);
        robot.drivetrain.updateModules();

        boolean dRS = gamepad1.right_stick_button;
        if (dRS && !pDRS) {
            xLock = !xLock;
        }
        pDRS = dRS;

        if (xLock) {
            robot.drivetrain.leftFrontModule.setTargetRotation(PI / 4);
            robot.drivetrain.rightFrontModule.setTargetRotation(-PI / 4);
            robot.drivetrain.rightRearModule.setTargetRotation(PI / 4);
            robot.drivetrain.leftRearModule.setTargetRotation(-PI / 4);
            robot.drivetrain.updateModules();
        }

//        robot.intake.loop();
//        robot.lift.loop();
        CommandScheduler.getInstance().run();

        robot.write();

        // Telemetry
//        telemetry.addData("liftPos:", robot.lift.getPos());
//        telemetry.addData("liftPow:", robot.lift.power);
//        telemetry.addData("liftTarget:", robot.lift.targetPosition);
//        telemetry.addData("intakePos:", robot.intake.getPos());
//        telemetry.addData("intakePow:", robot.intake.power);
//        telemetry.addData("intakeTarget:", robot.intake.targetPosition);
//        telemetry.addData("velocity:", robot.intake.curState.getV());
//        telemetry.addData("state:", robot.intake.curState.getV() == 0);
//        telemetry.addData("speed multiplier:", speedMultiplier);

        double loop = System.nanoTime();
        telemetry.addData("hz ", 1000000000 / (loop - loopTime));
        loopTime = loop;
        telemetry.update();

//        PhotonCore.EXPANSION_HUB.clearBulkCache();
        PhotonCore.CONTROL_HUB.clearBulkCache();
    }

    @Override
    public void reset() {
        CommandScheduler.getInstance().reset();
//        robot.intake.extension.resetEncoder();
//        robot.intake.extension.set(0);
//        robot.lift.lift.resetEncoder();
//        robot.lift.lift.set(0);
    }
}
