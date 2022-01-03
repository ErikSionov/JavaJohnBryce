package coupons.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import coupons.core.beans.Company;
import coupons.core.beans.Coupon;
import coupons.core.dao.CouponDbDao;
import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.CouponSystemException;
import coupons.core.exceptions.FacadeException;
import coupons.core.facade.AdminFacade;
import coupons.core.facade.ClientType;
import coupons.core.facade.LoginManager;
import coupons.core.job.CouponExpirationDailyJob;

public class Program {

	boolean programClose;
	Thread job;
	ConnectionPool con;
	CouponDbDao couponDao = new CouponDbDao();
	AdminFacade facade;

	public static void main(String[] args) {
		Program program = new Program();
		program.ProgramStart();
		 program.AdminLoginMenu();
	}

	public void menu() {

		JFrame frame = new JFrame("Coupon check");
		ImageIcon icon = new ImageIcon("files/logo.png");
		ImageIcon image = new ImageIcon("files/1.png");
		JTextField inputField = new JTextField();
		JLabel mainImage = new JLabel();
		String couponResult = "";
		JLabel textLabel_Coupon = new JLabel(couponResult);
		JButton button_GetCoupon = new JButton("Get Coupon");
		JButton button_Exit = new JButton("Exit");
		BaseButton button_AddCoupon = new BaseButton("Add New Coupon");
		BaseButton button_AddCompany = new BaseButton("Add New Company");

		frame.setVisible(true);
		frame.setBounds(400, 300, 1000, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(icon.getImage());
		frame.getContentPane().setBackground(new Color(0xFF00FF)); // can also take hexadecimal numbers 0x******
		frame.setLayout(null);

		inputField.setBounds(30, 200, 120, 30);
		mainImage.setBounds(8, 30, 300, 150);
		textLabel_Coupon.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20), "Coupon Info"));
		textLabel_Coupon.setBounds(405, 20, 900, 170);
		button_AddCoupon.setLocation(290, 200);
		button_AddCompany.setLocation(450, 200);
		button_GetCoupon.setBounds(160, 200, 120, 30);
		button_Exit.setBounds(850, 200, 100, 30);
		
		mainImage.setIcon(image);
		mainImage.setHorizontalTextPosition(JLabel.CENTER);
		mainImage.setVerticalTextPosition(JLabel.CENTER);
		mainImage.setForeground(new Color(0xFFFFFF));
		mainImage.setVerticalAlignment(JLabel.CENTER);
		mainImage.setHorizontalAlignment(JLabel.CENTER);

		System.out.println();
		textLabel_Coupon.setFont(new Font("Ariel", Font.BOLD, 15));

		button_GetCoupon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// retrieve a coupon of number printed in the text field
				int couponId = Integer.parseInt(inputField.getText());
				String str;
				try {
					Coupon c1 = couponDao.getOneCoupon(couponId);
					str = "<html>COUPON ID: " + c1.getId() + "   COMPANY ID: " + c1.getCompanyId() + "   CATEGORY: " + c1.getCategory().toString().toLowerCase() + "<br>" + "   TITLE: " + c1.getTitle() + "<br>" + "DESCRIPTION: " + c1.getDescription() + "<br>" + "   START DATE: " + c1.getStartDate() + "   EXPIRATION DATE: " + c1.getEndDate() + "<br>" + " AMOUNT OF COUPONS LEFT: " + c1.getAmount()
							+ "<br>" + "  PRICE: " + c1.getPrice() + "</html>";
					textLabel_Coupon.setText(str);
				} catch (CouponSystemException e1) {
					str = "no coupon with such ID present.";
					textLabel_Coupon.setText(str);
				}
			}
		});

		button_AddCoupon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				addCouponMenu();
				frame.setVisible(false);

			}
		});
		
		button_AddCompany.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				addCompanyMenu();
				frame.setVisible(false);

			}
		});
		
		button_Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProgramClose();
			}
		});

		frame.add(inputField);
		frame.add(mainImage);
		frame.add(textLabel_Coupon);
		frame.add(button_GetCoupon);
		frame.add(button_Exit);
		frame.add(button_AddCoupon);
		frame.add(button_AddCompany);

	}

	private void addCompanyMenu() {
		
		GridLayout grid = new GridLayout(0, 2, 10, 30);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(180, 180, 170));
		panel.setLayout(grid);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20), "Add company"));

		JFrame frame = new JFrame("Add New Company");

		BaseLabel lb_CompanyId = new BaseLabel("company Id:");
		BaseTextField tf_CompanyId = new BaseTextField("");

		BaseLabel lb_Name = new BaseLabel("Name:");
		BaseTextField tf_Name = new BaseTextField("");

		BaseLabel lb_Email = new BaseLabel("Email:");
		BaseTextField tf_Email = new BaseTextField("");

		BaseLabel lb_Password = new BaseLabel("Password: ");
		BaseTextField tf_Password = new BaseTextField("");

		BaseButton bt_AddCompany = new BaseButton("Add to DB");
		BaseButton bt_Back = new BaseButton("Back");

		// back button action
		bt_Back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				menu();

			}
		});

		// add company button action
		bt_AddCompany.addActionListener(new ActionListener() {
			Company company = new Company();
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					company.setId(Integer.parseInt(tf_CompanyId.getText()));
					company.setName(tf_Name.getText());
					company.setEmail(tf_Email.getText());
					company.setPassword(tf_Password.getText());

					facade.addCompany(company);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				JOptionPane.showMessageDialog(null, "Company added successfully as company number: " + company.getId(), "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		panel.add(lb_CompanyId);
		panel.add(tf_CompanyId);
		panel.add(lb_Name);
		panel.add(tf_Name);
		panel.add(lb_Email);
		panel.add(tf_Email);
		panel.add(lb_Password);
		panel.add(tf_Password);
		panel.add(bt_Back);
		panel.add(bt_AddCompany);

		frame.add(panel);
		frame.setVisible(true);
		frame.setBounds(400, 200, 300, 350);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(180, 180, 170)); // can also take hexadecimal numbers 0x******
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void addCouponMenu() {

		GridLayout grid = new GridLayout(0, 2, 10, 30);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(180, 180, 170));
		panel.setLayout(grid);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20), "Add coupon"));

		JFrame frame = new JFrame("Add New Coupon");

		BaseLabel lb_CouponId = new BaseLabel("coupon Id:");
		BaseTextField tf_CouponId = new BaseTextField("");

		BaseLabel lb_CompanyId = new BaseLabel("company Id:");
		BaseTextField tf_CompanyId = new BaseTextField("");

		BaseLabel lb_Category = new BaseLabel("category:");
		String[] choices = {"ELECTRONICS", "VACATION", "FOOD", "RESTAURANTS", "HOME", "CLOTHING", "CAMPING", "CONCERTS"};
		JComboBox<String> cb_Category = new JComboBox<>(choices);

		BaseLabel lb_Title = new BaseLabel("title:");
		BaseTextField tf_Title = new BaseTextField("");

		BaseLabel lb_Description = new BaseLabel("description:");
		BaseTextField tf_Description = new BaseTextField("");

		BaseLabel lb_StartDate = new BaseLabel("start date (yyyy-mm-dd):");
		BaseTextField tf_StartDate = new BaseTextField("");

		BaseLabel lb_EndDate = new BaseLabel("end date (yyyy-mm-dd):");
		BaseTextField tf_EndDate = new BaseTextField("");

		BaseLabel lb_Amount = new BaseLabel("amount:");
		BaseTextField tf_Amount = new BaseTextField("");

		BaseLabel lb_Price = new BaseLabel("price:");
		BaseTextField tf_Price = new BaseTextField("");

		BaseLabel lb_ImageLocation = new BaseLabel("image: ");
		BaseTextField tf_ImageLocation = new BaseTextField("");

		BaseButton bt_AddCoupon = new BaseButton("Add to DB");
		BaseButton bt_Back = new BaseButton("Back");

		// back button action
		bt_Back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				menu();

			}
		});

		// add coupon button action
		bt_AddCoupon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Coupon coupon = new Coupon();
					coupon.setId(Integer.parseInt(tf_CouponId.getText()));
					coupon.setCompanyId(Integer.parseInt(tf_CompanyId.getText()));
					coupon.setCategory(Coupon.Category.valueOf((String) cb_Category.getSelectedItem()));
					coupon.setTitle(tf_Title.getText());
					coupon.setDescription(tf_Description.getText());
					coupon.setStartDate(LocalDate.parse(tf_StartDate.getText()));
					coupon.setEndDate(LocalDate.parse(tf_EndDate.getText()));
					coupon.setAmount(Integer.parseInt(tf_Amount.getText()));
					coupon.setPrice(Double.parseDouble(tf_Price.getText()));
					coupon.setImage(tf_ImageLocation.getText());

					couponDao.addCoupon(coupon);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				JOptionPane.showMessageDialog(null, "Coupon added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		panel.add(lb_CouponId);
		panel.add(tf_CouponId);
		panel.add(lb_CompanyId);
		panel.add(tf_CompanyId);
		panel.add(lb_Category);
		panel.add(cb_Category);
		panel.add(lb_Title);
		panel.add(tf_Title);
		panel.add(lb_Description);
		panel.add(tf_Description);
		panel.add(lb_StartDate);
		panel.add(tf_StartDate);
		panel.add(lb_EndDate);
		panel.add(tf_EndDate);
		panel.add(lb_Amount);
		panel.add(tf_Amount);
		panel.add(lb_Price);
		panel.add(tf_Price);
		panel.add(lb_ImageLocation);
		panel.add(tf_ImageLocation);
		panel.add(bt_Back);
		panel.add(bt_AddCoupon);

		frame.add(panel);
		frame.setVisible(true);
		frame.setBounds(400, 200, 400, 600);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(180, 180, 170)); // can also take hexadecimal numbers 0x******
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void ProgramStart() {
		con = ConnectionPool.getInstance();
		job = new Thread(new CouponExpirationDailyJob());
		job.start();
	}

	protected void ProgramClose() {
		try {
			job.interrupt();
			job.join();
			ConnectionPool.getInstance().closeAllConnections();
		} catch (CouponSystemException e) {
			e.getMessage();
		} catch (InterruptedException e) {
			e.getMessage();
		}
		System.exit(0);
	}

	private void AdminLoginMenu() {

		JFrame frame = new JFrame("LOGIN MENU");
		JTextField textField_Email = new JTextField();
		JTextField textField_Password = new JTextField();
		JButton button_Login = new JButton("login");
		JLabel text_Email = new JLabel("email");
		JLabel text_Password = new JLabel("password");
		JLabel text_Error = new JLabel("error");

		text_Email.setBounds(65, 50, 150, 25);
		text_Email.setText("email");

		text_Error.setBounds(75, 100, 150, 25);
		text_Error.setText("error");
		text_Error.setFont(new Font("Ariel", Font.ITALIC, 10));
		text_Error.setForeground(Color.red);
		text_Error.setVisible(false);

		text_Password.setBounds(290, 50, 150, 25);
		text_Password.setText("password");

		frame.setBounds(300, 200, 600, 200);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(180, 180, 170));

		textField_Email.setBounds(100, 50, 150, 30);
		textField_Password.setBounds(350, 50, 150, 30);

		button_Login.setBounds(400, 100, 100, 30);
		button_Login.setFont(new Font("Ariel", Font.BOLD, 12));
		button_Login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginManager loginManager = LoginManager.getInstance();
				ClientType clientType = ClientType.ADMINISTRATOR;
				String email = textField_Email.getText();
				String pass = textField_Password.getText();

				try {
					facade = (AdminFacade) loginManager.login(email, pass, clientType);
					if (facade == null) {
						text_Error.setText("unknown user");
						text_Error.setForeground(Color.red);
						text_Error.setVisible(true);
					} else {
						text_Error.setText("welcome!");
						text_Error.setForeground(Color.blue);
						text_Error.setVisible(true);
						Thread.sleep(200);
						menu();
						frame.setVisible(false);
					}
				} catch (FacadeException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		frame.add(textField_Email);
		frame.add(textField_Password);
		frame.add(text_Email);
		frame.add(text_Error);
		frame.add(text_Password);
		frame.add(button_Login);
	}
}

class BaseButton extends JButton {

	private static final long serialVersionUID = 1L;

	BaseButton(String name) {
		int xSize = 150;
		int ySize = 30;
		this.setText(name);
		this.setSize(xSize, ySize);
	}
}

class BaseTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public BaseTextField(String name) {
		int xSize = 100;
		int ySize = 30;
		this.setText(name);
		this.setSize(xSize, ySize);
		this.setFont(new Font("Ariel", Font.PLAIN, 12));

	}
}

class BaseLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public BaseLabel(String text) {
		int xSize = 50;
		int ySize = 30;
		this.setText(text);
		this.setSize(xSize, ySize);
		this.setFont(new Font("Ariel", Font.BOLD, 14));
	}
}
