import java.util.*;
public class DriveTrain {
public static void main(String[] args) {

	//Instances
	Scanner scan = new Scanner(System.in);
	
	//Variables
	double Input = 0;
	double Turn = 0;
	double LeftMotor = 0;
	double RightMotor = 0;
	double gyro = 0;
	double fakegyro = 0;
	double zeros = 0;
	double front = 0;
	boolean turning = false;
	boolean allowfix = false;
	
	
	while(true){
		System.out.println("Gyro: ");
		gyro = scan.nextDouble();
		System.out.println("Input: ");
		Input = scan.nextDouble();
		System.out.println("Turn: ");
		Turn = scan.nextDouble();
		
		//Start Drive Code
		fakegyro = gyro - front;
		
		if(Turn == 0 || Turn == 0.0){
			turning = false;
		}
		else{
			turning = true;
		}
				
		//If Gyro goes above 360 or below -360 allow gyro allowfix again
		if(fakegyro >= 360 || fakegyro <= -360){
			allowfix = true;
		}
		
		//Keep Gyro Value Within -360 to 360
		if(gyro >= -5 && gyro <= 5){
			zeros = 0;
		}
		if(gyro <= zeros && (gyro % 360) <=5 && (gyro % 360) >=(-5) && allowfix == true){
			zeros = zeros - 360;
			allowfix = false;
		}
		if(gyro >= zeros && (gyro % 360) <=5 && (gyro % 360) >=(-5) && allowfix == true){
			zeros = zeros + 360;
			allowfix = false;
		}	
		
		//Correct Drive if not Turning
		if(turning==false){
			if(Math.abs((gyro-zeros) - 360) >= Math.abs((gyro-zeros) - 0)){
				LeftMotor = (Input + (((fakegyro-zeros)/360)));
				RightMotor = (Input - (((fakegyro-zeros)/360)));
			}	
			if(Math.abs((gyro-zeros) - 360) < Math.abs((gyro-zeros) - 0)){
				LeftMotor = (Input - (((fakegyro-zeros)/360)));
				RightMotor = (Input + (((fakegyro-zeros)/360)));
			}
		}
		//If turning set gyro's 0 to the new position 
		else{
			RightMotor = Turn;
			LeftMotor = (Turn*-1);
			front=gyro;
		}
		
		//Prevent Stalling Motors with really low Percents
		//if(LeftMotor < 0.001 && LeftMotor > 0.0001 || LeftMotor < -0.001 && LeftMotor > -0.0001){
		//	LeftMotor = 0;
		//}
		//if(RightMotor < 0.001 && RightMotor > 0.0001 || RightMotor < -0.001 && RightMotor > -0.0001){
		//	RightMotor = 0;
		//}
		//End Drive Code
		
		System.out.println("Input: " + Input);
		System.out.println("Left Motor: " + LeftMotor);
		System.out.println("Right Motor: " + RightMotor);
		System.out.println("Gyro: " + gyro);
		System.out.println("What Front Looks For: " + zeros);
		System.out.println("Front In Degrees (Changes When Turning): " + front);
		System.out.println("Allowfix: " + allowfix);
	}	
		
	
}
}
