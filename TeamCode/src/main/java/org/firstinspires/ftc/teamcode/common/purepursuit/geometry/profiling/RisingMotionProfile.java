package org.firstinspires.ftc.teamcode.common.purepursuit.geometry.profiling;

public class RisingMotionProfile implements MotionProfile {
    private final double maxV;
    private final double maxA;

    public RisingMotionProfile(double maxV, double maxA) {
        this.maxV = maxV;
        this.maxA = maxA;
    }

    @Override
    public double update(double time) {
        if (time < 0) {
            return 0;
        }
        double velocity = (maxA * time) / maxV;
        return Math.min(velocity, maxV);
    }
}
