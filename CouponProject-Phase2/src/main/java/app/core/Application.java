package app.core;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import app.core.tasks.DailyRemoveCouponsTask;

/** Coupon System application phase 2, Spring boot based.
 * @author Erik Sionov
 *
 */
@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		
		// scheduler check to delete all expired coupons from DB all other scheduled runs are exactly at 00:00 daily.
		DailyRemoveCouponsTask scheduled = ctx.getBean(DailyRemoveCouponsTask.class);
		scheduled.runAtApplicationStart();
		
		
		//===========================================================//
		//========TESTS RUNNING FROM APP.CORE.TESTS PACKAGE==========//
		//===========================================================//
		
		
		// slight delay before context close, to allow all test to run correctly.
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
		}
		ctx.close();

	}

	
	/**Utility method to format out-print of List objects.
	 * @param list
	 */
	public static void printList(List<?> list) {
		System.out.println("=======================");
		list.forEach(System.out::println);
		System.out.println("=======================");
	}

}
