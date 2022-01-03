package app.core.tasks;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.core.services.AdminService;

@Component
public class DailyRemoveCouponsTask {
	
	@Autowired
	AdminService adminService;
	long initialDelay = 1;
	
	@Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 24, initialDelayString = "PT0.06S")
	public void run() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>> begin scheduled coupon deletion");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		adminService.deleteAllOutdatedCoupons(LocalDate.now());
	}
	
}
