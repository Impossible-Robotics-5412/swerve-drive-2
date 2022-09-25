package org.firstinspires.ftc.teamcode.opmode.test.vision;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.common.powerplay.SleeveDetection;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

@Config
@Autonomous(name = "ColorTesting")
public class ColorTesting extends LinearOpMode {
    int width = 320, height = 240;
    OpenCvCamera cam;
    RelocalizerDetection pipeline;
    SleeveDetection pipeline2;

    @Override
    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        cam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new RelocalizerDetection();
        pipeline2 = new SleeveDetection();
        cam.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.

        cam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                cam.startStreaming(320,240, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });

        FtcDashboard.getInstance().startCameraStream(cam, 30);

        waitForStart();

        while (opModeIsActive())
        {

            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        }
    }
}

@Config
class RelocalizerDetection extends OpenCvPipeline {

    public static int COLOR_MAX = 49;
    public static int COLOR_MIN = 205;

    public static int r1 = 0, g1 = 0, b1 = 0;
    public static int r2 = 0, g2 = 0, b2 = 0;

//    private static final Scalar
//            lower_red_bounds  = new Scalar(COLOR_MIN, 0, 0, 255),
//            upper_red_bounds  = new Scalar(255, COLOR_MAX, COLOR_MAX, 255);
//            lower_blue_bounds = new Scalar(0, 0, COLOR_MIN, 255),
//            upper_blue_bounds = new Scalar(COLOR_MAX, COLOR_MAX, 255, 255);

    public static Scalar lower_red = new Scalar(COLOR_MIN, 0, 0, 255);
    public static Scalar upper_red = new Scalar(255, COLOR_MAX, COLOR_MAX, 255);

    Mat mask = new Mat();

    public RelocalizerDetection() {}

    @Override
    public void init(Mat frame) {}

    @Override
    public Mat processFrame(Mat input) {
        lower_red = new Scalar(r1, g1, b1, 255);
        upper_red = new Scalar(r2, g2, b2, 255);
        Mat processed = new Mat();

        //Imgproc.blur(input, processed, new Size(5, 5));

        Core.inRange(input, lower_red, upper_red, mask);
//        Imgproc.dilate(input, mask, new Mat());
//        Imgproc.dilate(input, mask, new Mat());

        Mat rgb = new Mat();
        //Imgproc.cvtColor(mask, rgb, Imgproc.COLOR_GRAY2BGR);
        Mat overlayMat = new Mat();
//        Core.copyTo(input, overlayMat, mask);
        processed.release();
        rgb.release();
        overlayMat.release();
        return mask;
    }
}
