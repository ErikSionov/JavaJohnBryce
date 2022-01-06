package app.core;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		//===========================================================//
		//========TESTS RUNNING FROM APP.CORE.TESTS PACKAGE==========//
		//===========================================================//
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		ctx.close();

	}

	public static void printList(List<?> list) {
		System.out.println("=======================");
		list.forEach(System.out::println);
		System.out.println("=======================");
	}

}
