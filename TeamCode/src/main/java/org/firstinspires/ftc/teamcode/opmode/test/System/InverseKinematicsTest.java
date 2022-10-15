package org.firstinspires.ftc.teamcode.opmode.test.System;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import org.firstinspires.ftc.teamcode.common.hardware.Robot;
import org.firstinspires.ftc.teamcode.opmode.teleop.OpMode;

public class InverseKinematicsTest extends OpMode {
    private Robot robot;

    public static double x, y, l;

    @Override
    public void initialize() {
        robot = new Robot(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


    }

    @Override
    public void run() {

    }
}
