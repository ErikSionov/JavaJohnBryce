package coupons.core.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import coupons.core.exceptions.CouponSystemException;

/**
 * singleton class that holds a pool of connections to be used with mySQL server connectivity. connection information and poolSize can be modified through the properties file.
 * 
 * @author Erik Sionov
 */
public class ConnectionPool {
	private static ConnectionPool instance;
	private Set<Connection> connections = new HashSet<Connection>();
	private int poolSize;
	private boolean isWaitingForAllConnection;

	public int getPoolSize() {
		return poolSize;
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			try {
				instance = new ConnectionPool();
			} catch (CouponSystemException e) {
				System.out.println("ConnectionPool() failed " +e.getMessage());
				e.printStackTrace();
			}
		}
		return instance;
	}

	/**
	 * retrieve one <code>connection</code> from the pool of available connections. if there are non available, would wait till one returns to the pool with a <code>restoreConnection</code> method. if <code>closeAllConnections</code> is trying to close all connections, this method wouldn't work untill all of them are closed.
	 * 
	 * @return one Connection() to be used with sql statements
	 * @throws CouponSystemException
	 * @throws RuntimeException
	 *             if trying to get a connection while <code>closeAllConnections</code> trying to close
	 */
	public synchronized Connection getConnection() throws CouponSystemException {

		if (isWaitingForAllConnection) {
			throw new RuntimeException("CONNECTION: getConnection() can't receive new connections while trying to CloseAllConnections()");
		}

		while (this.connections.isEmpty()) {
			try {
				System.out.println("WAITING: getConnection() waiting for an open connection in the pool");
				wait();
			} catch (InterruptedException e) {
				throw new CouponSystemException("THREAD: getConnection() waiting interrupted " + e.getMessage(), e);
			}
		}

		for (Connection con : this.connections) {
			if (con != null) {
				this.connections.remove(con);
				return con;
			}
		}

		throw new CouponSystemException("ERROR: getConnection() can't get an open connection in the pool");
	}

	/** stops all connection receiving from connection pool, and when all connection are back in the pool, closes them all
	 * @throws CouponSystemException
	 */
	public synchronized void closeAllConnections() throws CouponSystemException {

		while (connections.size() != poolSize) {
			try {
				isWaitingForAllConnection = true;
				System.out.println("WAITING: closeAllConnections() waiting for all connection to return to the pool");
				wait();
			} catch (InterruptedException e1) {
				throw new CouponSystemException("THREAD: closeAllConnections() waiting interrupted " + e1.getMessage(), e1);
			}
		}
		for (Connection con : connections) {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new CouponSystemException("ERROR: closeAllConnection() run into an error while closing a connection " + e.getMessage(), e);
				}
			}
		}
		System.out.println("closeAllConnectons() closed all connections.");
		isWaitingForAllConnection = false;
	}

	public synchronized void restoreConnection(Connection con) throws CouponSystemException {
		if (con == null) {
			throw new CouponSystemException("ERROR: can't return a null connection TO the pool");
		}
		// System.out.println("restoreConnection() restored one connection " + con.toString());
		connections.add(con);
		notify();
	}

	/**Connection pool COTR that builds the pool using configuration from the properties file
	 * @throws CouponSystemException
	 */
	private ConnectionPool() throws CouponSystemException {
		
		// load the properties file
		File file = new File("files/app.properties");
		Properties prop = new Properties();
		
		// create connections with pool size and store in the set
		try {
			
			prop.load(new FileInputStream(file));
			poolSize = Integer.parseInt(prop.getProperty("db.poolsize"));
			
			for (int i = 0; i < poolSize; i++) {
				Connection con = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
				connections.add(con);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: CONNECTION NOT ESTABLISHED!");
			throw new CouponSystemException("ConnectionPool() couldn't establish connection with DB " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: properties file not found.");
			throw new CouponSystemException("ConnectionPool() couldn't find properties file " + e.getMessage());
		} catch (IOException e) {
			System.out.println("ERROR: problem with IO of properties file.");
			throw new CouponSystemException("ConnectionPool() have IO file problems " + e.getMessage());
		}

		System.out.println("Connection pool created with " + poolSize + " connections.");
	}

}
