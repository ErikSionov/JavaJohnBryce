package coupons.core.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import coupons.core.dao.CouponDbDao;
import coupons.core.exceptions.CouponSystemException;

public class CouponExpirationDailyJob implements Runnable {

	private CouponDbDao couponsDao = new CouponDbDao();
	private boolean quit;

	// base COTR
	public CouponExpirationDailyJob() {
		
	}
	
	
	@Override
	public void run() {
		
		File file = new File("files/app.properties");
		
		Properties prop = new Properties();
		
		while (!quit) {
			try {
				//load properties file
				prop.load(new FileInputStream(file));
				int hoursToWait = Integer.parseInt(prop.getProperty("db.erase_coupons_per_hours"));
				
				System.out.println("THREAD: ExpiredCouponDelete cycle completed, next one in " + hoursToWait +" hours.");
				couponsDao.deleteAllExpiredCoupons(LocalDate.now());
				//sleep for cycle hours
				TimeUnit.HOURS.sleep(hoursToWait);
			} catch (InterruptedException e) {
				System.out.println("THREAD: expiredCoupons deletion job interrupted " + e.getMessage());
				quit = true;
			} catch (CouponSystemException e) {
				System.out.println("THREAD: deletion from DB incomplete " + e.getMessage());
				quit = true;
			} catch (IOException e) {
				System.out.println("THREAD: problem with properties file " + e.getMessage());
				quit = true;
			}
		}
	}
	
	public void stopThread() {
		Thread.currentThread().interrupt();
	}
	
}
