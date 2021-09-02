package bankSystem;

import java.util.ArrayList;

public class Logger
{
	private String driverName;

	public String getDriverName()
	{
		return driverName;
	}

	public void setDriverName(String driverName)

	{
		this.driverName = driverName;
	}
	
	public Logger(String name)
	{
		setDriverName(name);
	}
	
	public static void log(Log log)
	{
		System.out.println(log.toString());
	}
	
	public ArrayList<Log> getLogs()
	{
		ArrayList<Log> logs = new ArrayList<>();
		
		return logs;
	}
}
