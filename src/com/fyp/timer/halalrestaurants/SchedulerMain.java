package com.fyp.timer.halalrestaurants;

import java.util.Timer;

public class SchedulerMain {

	public static void main(String args[]) throws InterruptedException {
		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledTask mytask = new ScheduledTask(); // Instantiate SheduledTask class
		time.schedule(mytask,0,86400000); // Create Repetitively task for every 24 hours
	}
	
}

