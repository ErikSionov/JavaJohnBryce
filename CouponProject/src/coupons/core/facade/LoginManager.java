package coupons.core.facade;

import coupons.core.exceptions.FacadeException;

public class LoginManager {

	private static LoginManager instance;

	private LoginManager() {

	}

	public static LoginManager getInstance() {
		if (instance == null) {
			instance = new LoginManager();
		}

		return instance;
	}

	/** return a facade of the chosen type and only if the email and password are authenticated by DB
	 * @param <T> returns a facade type by the ClientType enum
	 * @param email 
	 * @param password
	 * @param clientType enum of ClientType()
	 * @return adminFacade(), CompanyFacade() or CustomerFacade() based on which clientType passed into parameter
	 * @throws FacadeException
	 */
	public ClientFacade login(String email, String password, ClientType clientType) throws FacadeException {
		
		try {
			switch (clientType) {
				case ADMINISTRATOR :
					AdminFacade adminF = new AdminFacade();
					if (adminF.login(email, password)) {
						return adminF;
					}
					return null;

				case COMPANY :
					CompanyFacade compF = new CompanyFacade();
					if (compF.login(email, password)) {
						return compF;
					}
					return null;

				case CUSTOMER :
					CustomerFacade custF = new CustomerFacade();
					if (custF.login(email, password)) {
						return custF;
					}
					return null;
					
				default :
					System.out.println("login() in loginManager failed, clientType not right");
					return null;
			}
		} catch (FacadeException e) {
			System.out.println("login() in loginManager failed");
			return null;
		}

	}

}
