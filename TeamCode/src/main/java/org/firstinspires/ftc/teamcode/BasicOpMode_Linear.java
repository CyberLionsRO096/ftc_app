package org.firstinspires.ftc.teamcode;/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
//@Disabled
public class BasicOpMode_Linear extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor Motorfata = null;
    private DcMotor Motorfata1 = null;
    private DcMotor MotorSpate  = null;
    private DcMotor MotorSpate1= null;
    private DcMotor Motorarm1 = null;
    private DcMotor Motorarm2 = null;
    private DcMotor MotorCombina= null;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        Motorfata = hardwareMap.get(DcMotor.class, "motor1");
        Motorfata1 = hardwareMap.get(DcMotor.class, "motor2");
        MotorSpate = hardwareMap.get(DcMotor.class, "motor3");
        MotorSpate1 = hardwareMap.get(DcMotor.class, "motor4");
        Motorarm1 = hardwareMap.get(DcMotor.class,  "motor5");
        Motorarm2 = hardwareMap.get(DcMotor.class,  "motor6");
        MotorCombina = hardwareMap.get(DcMotor.class,"motor7");
        MotorSpate1.setPower(0);
        MotorSpate.setPower(0);
        Motorfata.setPower(0);
        Motorfata1.setPower(0);
        Motorarm1.setPower(0);
        Motorarm2.setPower(0);
        MotorCombina.setPower(0);
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        Motorfata.setDirection(DcMotor.Direction.REVERSE);
        Motorfata1.setDirection(DcMotor.Direction.FORWARD);
        MotorSpate1.setDirection(DcMotor.Direction.FORWARD);
        MotorSpate.setDirection(DcMotor.Direction.REVERSE);
        Motorarm1.setDirection(DcMotor.Direction.FORWARD);
        Motorarm2.setDirection(DcMotor.Direction.FORWARD);
        MotorCombina.setDirection(DcMotor.Direction.FORWARD);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double left;
            double right;
            double armmotor;
            double armmotor1;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            //double drive = -gamepad1.left_stick_y;
            //double turn  =  gamepad1.right_stick_x;
           // left    = Range.clip(drive + turn, -1.0, 1.0) ;
            //right   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;
            armmotor = gamepad2.left_stick_y/2;
            armmotor1 = gamepad2.right_stick_y/2;
            if(gamepad2.x){
                MotorCombina.setPower(1);
            }
            if (gamepad2.y)
            {
                MotorCombina.setPower(-1);
            }

            // Send calculated power to wheels
            MotorSpate1.setPower(left);
            Motorfata.setPower(right);
            MotorSpate.setPower(right);
            Motorfata1.setPower(left);
            Motorarm1.setPower(armmotor);
            Motorarm2.setPower(armmotor1);
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", left, right);
            telemetry.update();
        }
    }
}
