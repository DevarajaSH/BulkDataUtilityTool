package TestCase;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Hashtable;
import java.util.Random;
import java.util.jar.*;

import javax.swing.JTextField;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Component;

import org.apache.tools.ant.util.FileUtils;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.github.javafaker.Faker;
import com.github.javafaker.shaded.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;

public class DatabaseTestUI_V5_5 {

	private JFrame frame;
	
	private Connection objConnection = null;
	private Statement objStatement = null;
	private ResultSet objResult = null;
	
	public static String strDataBaseServer = null;
	public static String strDataBaseName = null;
	public static String strDataBaseUserName = null;
	public static String strDataBasePassword = null;
	public static String strNumberOfRecordsToBeCreated = null;
	public static String strSelectedDatabaseType = null;
	public static String strSelectedTableName = null;
	private JPasswordField passwordField;
	public static String strEmployerAndHostName = null;
	public static String strLocationPrefix = null;
	private static PrintStream objprintStream = null;
	public static ArrayList<Integer> nObjectImageQSID = new ArrayList<Integer>();
	public static ArrayList<Integer> nLocationQSID = new ArrayList<Integer>();
	public static Hashtable<Integer, String> hLocationAndQSID = new Hashtable<Integer, String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					DatabaseTestUI_V5_5 window = new DatabaseTestUI_V5_5();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DatabaseTestUI_V5_5() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame("BulkData Insert Utility - v5.5");
		frame.setBounds(120, 120, 550, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblDBServer = new JLabel("Enter DB Server IP Address : ");
		lblDBServer.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDBServer.setHorizontalAlignment(SwingConstants.LEFT);
		lblDBServer.setBounds(new Rectangle(10, 10, 100, 30));
		
		JTextField DatabaseServerIP = new JTextField();
		DatabaseServerIP.setForeground(new Color(0, 0, 0));
		DatabaseServerIP.setBounds(new Rectangle(200, 50, 150, 30));
		DatabaseServerIP.setHorizontalAlignment(SwingConstants.LEFT);
		DatabaseServerIP.setToolTipText("Please enter database server IP");
		DatabaseServerIP.setColumns(20);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblDBServer, DatabaseServerIP}));

		JLabel lblDBName = new JLabel("Enter Database Name : ");
		lblDBName.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDBName.setHorizontalAlignment(SwingConstants.LEFT);
		lblDBName.setBounds(new Rectangle(220, 10, 100, 30));
		
		JTextField DatabaseName = new JTextField();
		DatabaseName.setForeground(new Color(0, 0, 0));
		DatabaseName.setBounds(new Rectangle(200, 80, 150, 30));
		DatabaseName.setHorizontalAlignment(SwingConstants.LEFT);
		DatabaseName.setToolTipText("Please enter Database Name");
		DatabaseName.setColumns(20);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblDBName, DatabaseName}));

		JLabel lblDBUsername = new JLabel("Enter Database Username : ");
		lblDBUsername.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDBUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblDBUsername.setBounds(new Rectangle(10, 50, 100, 30));
		
		JTextField DatabaseUserName = new JTextField();
		DatabaseUserName.setForeground(new Color(0, 0, 0));
		DatabaseUserName.setBounds(new Rectangle(200, 110, 150, 30));
		DatabaseUserName.setHorizontalAlignment(SwingConstants.LEFT);
		DatabaseUserName.setToolTipText("Please enter Database Name");
		DatabaseUserName.setColumns(20);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblDBUsername, DatabaseUserName}));

		JLabel lblDBPassword = new JLabel("Enter Database Password : ");
		lblDBPassword.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDBPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblDBPassword.setBounds(new Rectangle(10, 70, 100, 30));
		
		JLabel lblNumberOfRecordsToCreate = new JLabel("No. of records to Create :");
		lblNumberOfRecordsToCreate.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField NumberOfRecordsToCreate= new JTextField();
		NumberOfRecordsToCreate.setHorizontalAlignment(SwingConstants.LEFT);
		NumberOfRecordsToCreate.setToolTipText("Please enter number of records to insert");
		NumberOfRecordsToCreate.setColumns(10);
		
		JRadioButton rdbtnMSSQL = new JRadioButton("MSSQL");
		rdbtnMSSQL.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				strSelectedDatabaseType = "MSSQL";
			}
		});
		
		JRadioButton rdbtnOracle = new JRadioButton("Oracle");
		rdbtnOracle.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				strSelectedDatabaseType = "Oracle";
			}
		});

		ButtonGroup DatabaseGroup = new ButtonGroup ();
		DatabaseGroup.add (rdbtnMSSQL);
		DatabaseGroup.add (rdbtnOracle);
		JRadioButton rdbtnPersons = new JRadioButton("Persons");
		
		JRadioButton rdbtnVisitors = new JRadioButton("Visitors");
				
		JRadioButton rdbtnVisit = new JRadioButton("Visit");
		
		JRadioButton rdbtnLocation = new JRadioButton("Location");
		
		ButtonGroup TableGroup = new ButtonGroup ();
		TableGroup.add (rdbtnPersons);
		TableGroup.add (rdbtnVisitors);
		TableGroup.add (rdbtnVisit);
		TableGroup.add (rdbtnLocation);
		
		JLabel lblLocationPrefix = new JLabel("Location Prefix : ");
		lblLocationPrefix.setVisible(false);
		
		JTextField EmployerHostNametextField = new JTextField();
		EmployerHostNametextField.setColumns(10);
		EmployerHostNametextField.setVisible(false);
		
		rdbtnVisit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				strSelectedTableName = "Visit";
				if(rdbtnVisit.isSelected())
				{
					lblLocationPrefix.setVisible(true);
					lblLocationPrefix.setText("Host Name : ");
					EmployerHostNametextField.setVisible(true);
				}
			}
		});
		rdbtnPersons.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				strSelectedTableName = "Persons";
				if(rdbtnPersons.isSelected())
				{
					lblLocationPrefix.setVisible(true);
					lblLocationPrefix.setText("Employer Name : ");
					EmployerHostNametextField.setVisible(true);
				}
			}
		});
		rdbtnVisitors.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				strSelectedTableName = "Visitors";
				if(rdbtnVisitors.isSelected())
				{
					lblLocationPrefix.setVisible(false);
					EmployerHostNametextField.setVisible(false);
				}
			}
		});
		
		rdbtnLocation.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				strSelectedTableName = "Location";
				if(rdbtnLocation.isSelected())
				{
					lblLocationPrefix.setVisible(true);
					lblLocationPrefix.setText("Location Prefix : ");
					EmployerHostNametextField.setVisible(true);
				}
			}
		});
		
		JLabel lblDatabaseType = new JLabel("Database Type : ");		
		JLabel lblTableToPopulate = new JLabel("Action to performed : ");
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (DatabaseServerIP.getText().isEmpty() && DatabaseName.getText().isEmpty() && 
						DatabaseUserName.getText().isEmpty() && passwordField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, " All fields are cleared !");
				}
				else
				{
					DatabaseServerIP.setText(null); 
					DatabaseName.setText(null); 
					DatabaseUserName.setText(null); 
					passwordField.setText(null); 
					NumberOfRecordsToCreate.setText(null);
					EmployerHostNametextField.setText(null);
					System.out.println("All fields cleared");
				}
			}
		});
		
		JButton btnTestDBConnection = new JButton("Test Connection");
		btnTestDBConnection.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					if(DatabaseServerIP.getText().isEmpty() || DatabaseName.getText().isEmpty() || 
							DatabaseUserName.getText().isEmpty() || passwordField.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Please enter all the fields");
					else
					{
						strDataBaseServer = DatabaseServerIP.getText(); 
						strDataBaseName = DatabaseName.getText(); 
						strDataBaseUserName = DatabaseUserName.getText(); 
						strDataBasePassword = passwordField.getText(); 
						strNumberOfRecordsToBeCreated = NumberOfRecordsToCreate.getText();
						strEmployerAndHostName = EmployerHostNametextField.getText();
						if(DatabaseConnectivity ())
							JOptionPane.showMessageDialog(null, "Test Database connectivity is successful !");
						else
							JOptionPane.showMessageDialog(null, "Test Database connection is Failed");
					}
				} 
				catch (HeadlessException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		JButton btnRunOrSubmit = new JButton("Run/Submit");
		btnRunOrSubmit.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(DatabaseServerIP.getText().isEmpty() || DatabaseName.getText().isEmpty() || 
						DatabaseUserName.getText().isEmpty() || passwordField.getText().isEmpty() || NumberOfRecordsToCreate.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter all the fields");
				else
				{
					strDataBaseServer = DatabaseServerIP.getText(); 
					strDataBaseName = DatabaseName.getText(); 
					strDataBaseUserName = DatabaseUserName.getText(); 
					strDataBasePassword = passwordField.getText(); 
					strNumberOfRecordsToBeCreated = NumberOfRecordsToCreate.getText();
					strEmployerAndHostName = EmployerHostNametextField.getText();
					try 
					{
						if (strSelectedDatabaseType == null)
							JOptionPane.showMessageDialog(null, "Please select Database type !");
						else if (strSelectedTableName == null)
							JOptionPane.showMessageDialog(null, "Please select the table to Insert data !");
						else if(strSelectedTableName.equals("Persons"))
						{
							if(objConnection == null || objConnection.isClosed())
								DatabaseConnectivity ();
							strEmployerAndHostName = EmployerHostNametextField.getText();
							if(InsertIntoPersonsTable ("Persons"))
							{
								ExecuteStoreProcedure ();
					   	 		objConnection.close();
							}
						}
						else if(strSelectedTableName.equals("Visitors"))
						{
							if(objConnection == null || objConnection.isClosed())
								DatabaseConnectivity ();
							if(InsertIntoVisitorTable ())
							{
								ExecuteStoreProcedure ();
					   	 		objConnection.close();
							}
						}
						else if(strSelectedTableName.equals("Location"))
						{
							if(objConnection == null || objConnection.isClosed())
								DatabaseConnectivity ();
							strLocationPrefix = EmployerHostNametextField.getText();
							if(InsertIntoLocationTable ())
							{
								int nLength = hLocationAndQSID.size();
								for(int i=1;i<=nLength;i++)
								{
									String strLocationName = hLocationAndQSID.get(nLocationQSID.get(i-1));
									String strLocationQSID = Integer.toString(nLocationQSID.get(i-1));
									InsertIntoLocationBuildingTable (3, strLocationName, strLocationQSID);
								}
								ExecuteStoreProcedure ();
					   	 		objConnection.close();
							}
						}
					} 
					catch (HeadlessException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
						try {
							objConnection.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}          
		}); 
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBounds(32, 211, 250, 187);
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		frame.getContentPane().add(scrollPane);
		objprintStream = new PrintStream(new CustomOutputStream(textArea));
		scrollPane.setViewportView(textArea);
		System.setOut(objprintStream);
		System.setErr(objprintStream);
		//saveFile("D:\\a.txt", "");
		//File f = new File("D:\\a.txt");
	    //FileOutputStream(f, false));
	    //  objprintStream.println(str);
	    //  objprintStream.close();

		JButton btnClose = new JButton("Clear Log");
		btnClose.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				textArea.removeAll();
				textArea.setText(null);
			}
		});
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Please enter Database Password");
		passwordField.setColumns(20);
		
		passwordField.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
		//JButton btnNewButton = new JButton("", new ImageIcon("./images/images.jpg"));
		JButton btnNewButton = new JButton("Help");
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
		btnNewButton.setToolTipText("Please click on the link for help");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String strText = " Bulk Data Tool Version : 5.5 "
						+ "\n Release date : 2019-01-13"
						+"\n"
						+ "\n For any information and queries, please contact "
						+ "\n Gautham Pai <gpai@hidglobal.com> "
						+ "\n Devaraja Lakshmana <devrajal@hidglobal.com>";
				JOptionPane.showMessageDialog (null, strText, "Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(73, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(40)
									.addComponent(lblDBServer))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(40)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDBName)
										.addComponent(lblDBUsername)
										.addComponent(lblDBPassword)
										.addComponent(lblNumberOfRecordsToCreate)
										.addComponent(lblDatabaseType)
										.addComponent(lblTableToPopulate))
									.addGap(45)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(rdbtnOracle)
											.addGap(47)
											.addComponent(rdbtnMSSQL))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(NumberOfRecordsToCreate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(DatabaseName, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
											.addComponent(DatabaseUserName)
											.addComponent(passwordField)
											.addComponent(DatabaseServerIP, Alignment.TRAILING))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(EmployerHostNametextField, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(rdbtnVisitors)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(rdbtnPersons)))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(rdbtnVisit)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rdbtnLocation))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(42)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnClear)
											.addGap(18)
											.addComponent(btnTestDBConnection)
											.addGap(18)
											.addComponent(btnRunOrSubmit)
											.addGap(18)
											.addComponent(btnClose))
										.addComponent(lblLocationPrefix))))
							.addContainerGap())
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDBServer)
						.addComponent(DatabaseServerIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(lblDBName))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(DatabaseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDBUsername)
						.addComponent(DatabaseUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDBPassword))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumberOfRecordsToCreate)
						.addComponent(NumberOfRecordsToCreate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblDatabaseType))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(rdbtnOracle)
							.addComponent(rdbtnMSSQL)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTableToPopulate)
						.addComponent(rdbtnVisitors)
						.addComponent(rdbtnPersons)
						.addComponent(rdbtnVisit)
						.addComponent(rdbtnLocation))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLocationPrefix)
						.addComponent(EmployerHostNametextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClear)
						.addComponent(btnTestDBConnection)
						.addComponent(btnRunOrSubmit)
						.addComponent(btnClose))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		//action listener
  }
	
	public boolean DatabaseConnectivity () throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException  
	{
		 boolean bDatabaseConnect = false;
		  try
		  {
			  if(strSelectedDatabaseType.equalsIgnoreCase("MSSQL"))
			  {
				  System.out.println("Connecting to Database : "+strSelectedDatabaseType);
				  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance(); 
				  String strURL = "jdbc:sqlserver://"+strDataBaseServer+";"+"DatabaseName="+strDataBaseName;	
				  objConnection = DriverManager.getConnection(strURL, strDataBaseUserName, strDataBasePassword);
				  objStatement = objConnection.createStatement();
				  bDatabaseConnect = true;
				  System.out.println("Database Connection is Sucessful"); 
			  }
			  else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
			  {
				  System.out.println("Connecting to Database : "+strSelectedDatabaseType);
				  Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
				  //String strURL = "jdbc:oracle:thin:@192.168.6.59:1521/ORADB05";
				  String strURL = "jdbc:oracle:thin:@"+strDataBaseServer+":1521/"+strDataBaseName;
				  objConnection=DriverManager.getConnection(strURL,strDataBaseUserName, strDataBasePassword);  
				  if(!objConnection.isClosed())
				  {
					  bDatabaseConnect = true;
					  System.out.println("Oracle Database Connection is Sucessful"); 
				  }
				  else
					  System.out.println("Failed to connect Oracle Database");
			  }
			  else if(strSelectedDatabaseType.isEmpty())
			  {
				  System.out.println("Unable to connect Database");   
			  }
	 	  }
		  catch (HeadlessException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) 
		  {
			 System.out.println("Database Connection is Failed"); 
			 e.printStackTrace();
		  }
		  return bDatabaseConnect;
	}
	
	public boolean InsertIntoVisitorTable ()
	{
		boolean bVisitorCreated = false;
		try
		{
			 System.out.println("Inserting data into Visitor table");
			 LocalDateTime objCurrentTimeBeforeInsert = LocalDateTime.now();
			 String strSqlVisitor = "INSERT INTO VISITORS (QSID,COMPANY, COUNTRY, EMAIL, FIRSTNAME, LASTNAME, OWNERID, PHONE,WATCHLIST,VISITORTYPE)\n" + 
	 		  		"VALUES (?,?, ?, ?, ?, ?, ?, ?,?,?)";
	 		 PreparedStatement PSVisitor = objConnection.prepareStatement(strSqlVisitor);
	 		 Faker objfaker = new Faker();
	 		 int strMaxVisitorQSID = GetMaxVisitorQSID ();
	 		 int TotalNumberOfRecord=Integer.parseInt(strNumberOfRecordsToBeCreated);
	 		 String ntest = Integer.toString(strMaxVisitorQSID+1);
	 		 for (int i = 1; i <=TotalNumberOfRecord; i++) 
	 		 {
	 			String strFirstName = objfaker.name().firstName()+getRandomString(3);
				String strLastName =  objfaker.name().lastName();
				String strEmailID = "ATGTest2020@gmail.com"; 
				System.out.println("Visitor Created : "+" " +i + " "+strFirstName+ " "+strLastName);
				PSVisitor.setString(1, Integer.toString(strMaxVisitorQSID+i));
				PSVisitor.setString(2, objfaker.company().name());
				PSVisitor.setString(3, objfaker.address().country());
				PSVisitor.setString(4, strEmailID);
				PSVisitor.setString(5, strFirstName);
				PSVisitor.setString(6, strLastName);					
				PSVisitor.setString(7, "2");
				PSVisitor.setString(8, objfaker.phoneNumber().cellPhone());
				PSVisitor.setString(9, "0");
				PSVisitor.setString(10, "Visitor");
				PSVisitor.addBatch();
	 		}
	 		PSVisitor.executeBatch(); // insert remaining records
	 		PSVisitor.close();
	 		bVisitorCreated = true;
	 		LocalDateTime objCurrentTimeAfterInsert = LocalDateTime.now();
	 		Duration duration = Duration.between(objCurrentTimeAfterInsert, objCurrentTimeBeforeInsert);
	 		int ndiff = (int)Math.abs(duration.getSeconds());
			int hours = ndiff / 3600;
			int minutes = (ndiff % 3600) / 60;
			int seconds = ndiff % 60;
	 		System.out.println("Total number of Visitors Inserted: "+TotalNumberOfRecord+ ". Time taken to created these Visitors: "+hours+" hh: "+minutes+" mm: "+seconds+ " ss");
		}
		catch (SQLException e) 
		{
			System.out.println("Error while inserting Visitors, hence no Visitors inserted.");
			e.printStackTrace();
		}
		return bVisitorCreated;
	}
	
	public boolean InsertIntoLocationTable ()
	{
		boolean bLocationCreated = false;
		try
		{
			 System.out.println("Inserting data into Location table");
			 LocalDateTime objCurrentTimeBeforeInsert = LocalDateTime.now();
			 String strSqlLocation = "INSERT INTO Locations (QSID, NAME, CITY, STATE, ZIP, LOCATIONCODE, COUNTRY, ADDRESSLINE1, ADDRESSLINE2, DESCRIPTION, MANAGEDBY, STATUS, LATITUDE, LONGITUDE, CANACCEPTVISIT, REGION)\n" + 
	 		  		"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 		 PreparedStatement PSLocation = objConnection.prepareStatement(strSqlLocation);
	 		 Faker objfaker = new Faker();
	 		 int nMaxLocationQSID = GetMaxLocationQSID ();
	 		 int TotalNumberOfRecord=Integer.parseInt(strNumberOfRecordsToBeCreated);
	 		 hLocationAndQSID.clear();
	 		 if (TotalNumberOfRecord>0 && TotalNumberOfRecord<=3000)
	 		 {
		 		 for (int i = 1; i <=TotalNumberOfRecord; i++) 
		 		 {
		 			String strLocationName = strLocationPrefix+"_"+getRandomString(8);
					System.out.println("Location Created : "+" " +i + " "+strLocationName+ " ");
					PSLocation.setString(1, Integer.toString(nMaxLocationQSID+i));
					nLocationQSID.add(nMaxLocationQSID+i);
					hLocationAndQSID.put(nMaxLocationQSID+i, strLocationName);
					PSLocation.setString(2, strLocationName);
					//PSLocation.setString(3, objfaker.address().cityName());
					PSLocation.setString(3, "Anderson");
					//PSLocation.setString(4, objfaker.address().stateAbbr());
					PSLocation.setString(4, "CA");
					PSLocation.setString(5, objfaker.address().zipCode());
					PSLocation.setString(6, strLocationName);					
					//PSLocation.setString(7, objfaker.address().countryCode());
					PSLocation.setString(7, "USA");
					PSLocation.setString(8, objfaker.address().streetAddress());
					PSLocation.setString(9, objfaker.address().secondaryAddress());
					PSLocation.setString(10, "Location Created using data utility tool");
					PSLocation.setString(11, "1");
					PSLocation.setString(12, "Active");
					PSLocation.setString(13, objfaker.address().latitude());
					PSLocation.setString(14, objfaker.address().longitude());
					PSLocation.setString(15, Integer.toString(1));
					PSLocation.setString(16, "North America");
					PSLocation.addBatch();
		 		}
		 		bLocationCreated=true;
		 		PSLocation.executeBatch(); // insert remaining records
		 		PSLocation.close();
		 		LocalDateTime objCurrentTimeAfterInsert = LocalDateTime.now();
		 		Duration duration = Duration.between(objCurrentTimeAfterInsert, objCurrentTimeBeforeInsert);
		 		int ndiff = (int)Math.abs(duration.getSeconds());
				int hours = ndiff / 3600;
				int minutes = (ndiff % 3600) / 60;
				int seconds = ndiff % 60;
		 		System.out.println("Total number of Locations Inserted: "+TotalNumberOfRecord+ ". Time taken to created these Locations: "+hours+" hh: "+minutes+" mm: "+seconds+ " ss");
	 		}
	 		else
	 		{
	 			bLocationCreated=false;
	 			System.out.println("We are not allowing to create more than 3000 locations");
	 		}
		}
		catch (SQLException e) 
		{
			System.out.println("Error while inserting Locations, hence no Location created.");
			e.printStackTrace();
		}
		return bLocationCreated;
	}
	
	public boolean InsertIntoLocationBuildingTable (int TotalNumberOfRecord, String strLocation, String strLocationQSID)
	{
		boolean bLocationBuildingCreated = false;
		try
		{
			 System.out.println("Inserting data into Building for the Location : "+strLocation);
			 String strSqlBuildingLocation = "INSERT INTO LOCATIONBUILDINGS(QSID, LOCATIONID, BUILDING, ADDRESSLINE1, ADDRESSLINE2, DESCRIPTION, COUNTRY, STATE, STATUS, CITY, CANACCEPTVISIT)\n" + 
	 		  		"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 		 PreparedStatement PSLocationBuilding = objConnection.prepareStatement(strSqlBuildingLocation);
	 		 Faker objfaker = new Faker();
	 		 int nMaxBuildingQSID = GetMaxBuildingQSID ();
	 		 if (TotalNumberOfRecord>0 && TotalNumberOfRecord<=3)
	 		 {
		 		 for (int i = 1; i <=TotalNumberOfRecord; i++) 
		 		 {
		 			String strBuildingName = strLocationPrefix+"_"+"Building"+i;
					System.out.println("Location Building Created : "+" " +i + " "+strBuildingName+ " ");
					PSLocationBuilding.setString(1, Integer.toString(nMaxBuildingQSID+i));
					PSLocationBuilding.setString(2, strLocationQSID);
					PSLocationBuilding.setString(3, strBuildingName);
					PSLocationBuilding.setString(4, objfaker.address().streetAddress());
					PSLocationBuilding.setString(5, objfaker.address().secondaryAddress());
					PSLocationBuilding.setString(6, "Building Created using data utility tool");					
					//PSLocationBuilding.setString(7, objfaker.address().countryCode());
					PSLocationBuilding.setString(7, "USA");
					//PSLocationBuilding.setString(8, objfaker.address().stateAbbr());
					PSLocationBuilding.setString(8, "CA");
					PSLocationBuilding.setString(9, "Active");
					//PSLocationBuilding.setString(10, objfaker.address().cityName()); 
					PSLocationBuilding.setString(10, "Anderson");
					PSLocationBuilding.setString(11, "1");
					PSLocationBuilding.addBatch();
		 		}
		 		bLocationBuildingCreated = true;
		 		PSLocationBuilding.executeBatch(); // insert remaining records
		 		PSLocationBuilding.close();
	 		}
	 		 else
	 			System.out.println("We are not allowing to create more than 3 Buildings");
		}
		catch (SQLException e) 
		{
			System.out.println("Error while inserting Building, hence Location Building is not created.");
			e.printStackTrace();
		}
		return bLocationBuildingCreated;
	}
	
	public void ExecuteStoreProcedure ()
	{
		try
		{
			String strExecProcedure = "";
			if(strSelectedDatabaseType.equalsIgnoreCase("MSSQL"))
 		     strExecProcedure = "EXEC UPDATEOBJECTIDS";
			 else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
			 {
				strExecProcedure = "begin UPDATEOBJECTIDS; "
						+ "end;";
			 }
	 		 PreparedStatement ps = objConnection.prepareStatement(strExecProcedure);
	 		 ps.execute();
	 		 System.out.println("Updated UPDATEOBJECTIDS Store Procedure as well");
             ps.close();
	 	}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public int GetMaxVisitorQSID ()
	{
		 String strMaxValue = "";
		 int maxqsid;
		 try
		 {
			 String strSqlSelectMaxQSID = "";
			 PreparedStatement objSelectPS = null;
			 if(strSelectedDatabaseType.equalsIgnoreCase("MSSQL"))
				 strSqlSelectMaxQSID= "SELECT isnull(max(QSID),0) AS QSID FROM VISITORS";
			 else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
				 strSqlSelectMaxQSID= "SELECT NVL(max(QSID),0) AS QSID FROM VISITORS";
			 objSelectPS  = this.objConnection.prepareStatement(strSqlSelectMaxQSID);
		     objResult = objSelectPS.executeQuery();
	         if(objResult.next())
	         {
	             strMaxValue = objResult.getString("QSID");
	             if(!strMaxValue.isEmpty())
	            	 objSelectPS.close();
	         }
		 }
		 catch(Exception e)
		 {
			e.printStackTrace();
		 }
		 maxqsid=Integer.parseInt(strMaxValue);
		 return maxqsid;
	}
	
	public int GetMaxLocationQSID ()
	{
		 String strMaxValue = "";
		 int maxqsid;
		 try
		 {
			 String strSqlSelectMaxQSID = "";
			 PreparedStatement objSelectPS = null;
			 if(strSelectedDatabaseType.equalsIgnoreCase("MSSQL"))
				 strSqlSelectMaxQSID= "SELECT isnull(max(QSID),0) AS QSID FROM LOCATIONS";
			 else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
				 strSqlSelectMaxQSID= "SELECT NVL(max(QSID),0) AS QSID FROM LOCATIONS";
			 objSelectPS  = this.objConnection.prepareStatement(strSqlSelectMaxQSID);
		     objResult = objSelectPS.executeQuery();
	         if(objResult.next())
	         {
	             strMaxValue = objResult.getString("QSID");
	             if(!strMaxValue.isEmpty())
	            	 objSelectPS.close();
	         }
		 }
		 catch(Exception e)
		 {
			e.printStackTrace();
		 }
		 maxqsid=Integer.parseInt(strMaxValue);
		 return maxqsid;
	}
	
	public int GetMaxBuildingQSID ()
	{
		 String strMaxValue = "";
		 int maxqsid;
		 try
		 {
			 String strSqlSelectMaxQSID = "";
			 PreparedStatement objSelectPS = null;
			 if(strSelectedDatabaseType.equalsIgnoreCase("MSSQL"))
				 strSqlSelectMaxQSID= "SELECT isnull(max(QSID),0) AS QSID FROM LOCATIONBUILDINGS";
			 else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
				 strSqlSelectMaxQSID= "SELECT NVL(max(QSID),0) AS QSID FROM LOCATIONBUILDINGS";
			 objSelectPS  = this.objConnection.prepareStatement(strSqlSelectMaxQSID);
		     objResult = objSelectPS.executeQuery();
	         if(objResult.next())
	         {
	             strMaxValue = objResult.getString("QSID");
	             if(!strMaxValue.isEmpty())
	            	 objSelectPS.close();
	         }
		 }
		 catch(Exception e)
		 {
			e.printStackTrace();
		 }
		 maxqsid=Integer.parseInt(strMaxValue);
		 return maxqsid;
	}
	
	public boolean InsertIntoPersonsTable (String strValue) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException  
	 {
		boolean bPersonInsert = false;
		  try
		  {
			  System.out.println("Inserting data into Person table");
			  LocalDateTime objCurrentTimeBeforeInsert = LocalDateTime.now();
			  PreparedStatement ps = null;
			  int TotalNumberOfRecord = 0;
			  int nEmployerID=-1;
			  if(!strEmployerAndHostName.isEmpty())
				  nEmployerID= GetEmployerIDFromName ();
			  if(nEmployerID == 0)
			  {
				  String strText = "Entered Employer name "+strEmployerAndHostName+" is not present in the system";
				  JOptionPane.showMessageDialog (null, strText, "Information", JOptionPane.INFORMATION_MESSAGE);
			  }
			  if((strValue.equals("Persons") && nEmployerID==-1) || (strValue.equals("Persons") && nEmployerID>0))
			  {
		 		  String strSqlPersons = "INSERT INTO PERSONS (QSID,FIRSTNAME,LASTNAME, NAME, TYPE, STATUS, USERNAME,PRIMARYID,VISITORCANINV,VISITORESCRIN,IS_SELFSERVICE_USER,EMAIL,EMPLOYER, PICTURE)\n" + 
		 		  		"VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 		  ps = objConnection.prepareStatement(strSqlPersons);
		 		  Faker objfaker = new Faker();
		 		  int nMaxPersonsQSID = GetMaxPersonsQSID ();
		 		  String strMaxPrimaryID=GetMaxPrimaryID();
		 		 File ImageFile = new File("./Test_Image.jpg");
		 		 if(ImageFile.exists())
		 			 InsertIntoObjectImages ();
		 		 else
		 			 System.out.println("Image file not found, hence Persons are not inserted with profile image");
		 		  int pid=Integer.parseInt(strMaxPrimaryID.substring(2));
		 		  pid++;
		 		  TotalNumberOfRecord=Integer.parseInt(strNumberOfRecordsToBeCreated);
				  for (int i = 1; i <=TotalNumberOfRecord; i++) 
		 		  {
		 			 String strFirstName = objfaker.name().firstName()+getRandomString(3);
			 		 String strLastName =  objfaker.name().lastName();
			 		 String strEmailID = "ATGTest2020@gmail.com";		 		 
			 		 System.out.println("Persons Created : "+" " +i + " "+strFirstName+ " "+strLastName);
			 		 ps.setString(1, Integer.toString(nMaxPersonsQSID+i));
			  		 ps.setString(12, strEmailID);
			 		 ps.setString(2,  strFirstName);
			 		 ps.setString(3, strLastName);
		 			 ps.setString(4, strFirstName + " "+ strLastName);					
		 			 ps.setString(5, "3");
		 			 ps.setString(6, "Active");
		 			 ps.setString(7, strFirstName + strLastName);		
		 			 ps.setString(8, "NE"+Integer.toString(pid+i));
		 			 ps.setString(9, "1");
		 			 ps.setString(10, "1");
		 			 ps.setString(11, "1");
		 			 if(nEmployerID<=0)
		 				 ps.setString(13, null);
		 			 else if (nEmployerID>0)
		 			     ps.setString(13, Integer.toString(nEmployerID));
		 			 if(nObjectImageQSID.size()>0)
		 				 ps.setString(14, Integer.toString(nObjectImageQSID.get(i-1)));
		 			 else if (nObjectImageQSID.size()==0)
		 				 ps.setString(14, null);
		             ps.addBatch();
		 		 }
		 		 ps.executeBatch(); // insert remaining records
		 		 ps.close();
		 		 bPersonInsert = true;
			 }
	 		 LocalDateTime objCurrentTimeAfterInsert = LocalDateTime.now();
	 		 Duration duration = Duration.between(objCurrentTimeAfterInsert, objCurrentTimeBeforeInsert);
	 	     int ndiff = (int)Math.abs(duration.getSeconds());
			 int hours = ndiff / 3600;
			 int minutes = (ndiff % 3600) / 60;
			 int seconds = ndiff % 60;
	 		 System.out.println("Total number of Persons Inserted: "+TotalNumberOfRecord+ ". Time taken to created these Persons: "+hours+" hh: "+minutes+" mm: "+seconds+ " ss");
	 	  }
		  catch(Exception e)
		  {
			  System.out.println("Error while inserting Persons, hence no Persons inserted.");
			  e.printStackTrace();
		  }
		  return bPersonInsert;
	  }
		 
	 public int GetMaxPersonsQSID ()
	 {
		 String strMaxValue = "";
		 int maxqsid;
		 try
		 { 
			 PreparedStatement objSelectPS = null;
			 String strSqlSelectMaxQSID = "";
			 if(strSelectedDatabaseType.equalsIgnoreCase("MSSQL"))
				 strSqlSelectMaxQSID= "SELECT isnull(max(QSID),0) AS QSID FROM PERSONS";
			 else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
				 strSqlSelectMaxQSID= "SELECT NVL(max(QSID),0) AS QSID FROM PERSONS";
			 objSelectPS  = this.objConnection.prepareStatement(strSqlSelectMaxQSID);
		     objResult = objSelectPS.executeQuery();
	         if(objResult.next())
	         {
	             strMaxValue = objResult.getString("QSID");
	             if(!strMaxValue.isEmpty())
	            	 objSelectPS.close();
	         }
		 }
		 catch(Exception e)
		 {
			  e.printStackTrace();
		 }
		 maxqsid=Integer.parseInt(strMaxValue);
		 return maxqsid;
	 }
	 
	 public String GetMaxPrimaryID ()
	 {
		 String strMaxPrimaryID = "";
		 try
		 { 
			 PreparedStatement objSelectPS = null;
			 String strSqlSelectMaxQSID = "SELECT max(PRIMARYID) AS PrimaryID FROM PERSONS";
			 objSelectPS  = this.objConnection.prepareStatement(strSqlSelectMaxQSID);
		     objResult = objSelectPS.executeQuery();
	         if(objResult.next())
	         {
	        	 strMaxPrimaryID = objResult.getString("PrimaryID");
		             if(!strMaxPrimaryID.isEmpty())
	            	 objSelectPS.close();
	         }
	         else
	        	 strMaxPrimaryID = "NE01";
		 }
		 catch(Exception e)
		 {
			  e.printStackTrace();
		 }
		 return strMaxPrimaryID;
	 }
	 
	 /*
	  * *****
	  * **************
	  * Needs to be changed to Oracle */
	 public String GetHostIDFromName ()
	 {
		 String strHostID = "";	
		 try
		 { 
			 PreparedStatement objSelectPS = null;
			 String strSqlSelectMaxQSID = "";		 
			 if(strSelectedDatabaseType.equalsIgnoreCase("MSSQL"))
				 strSqlSelectMaxQSID= "SELECT top 1 QSID, Name FROM PERSONS WHERE NAME LIKE '%"+strEmployerAndHostName+"%';";
			 else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
				 strSqlSelectMaxQSID= "SELECT * FROM (SELECT QSID,NAME FROM Persons WHERE NAME LIKE '%"+strEmployerAndHostName+"%' ORDER BY NAME ) WHERE rownum = 1;"; 
			 objSelectPS  = this.objConnection.prepareStatement(strSqlSelectMaxQSID);
		     objResult = objSelectPS.executeQuery();
	         if(objResult.next())
	         {
		             if(objResult.getString("Name").equalsIgnoreCase(strEmployerAndHostName))
		             {
			        	 strHostID = objResult.getString("QSID");
			        	 if(!strHostID.isEmpty())
		            	 objSelectPS.close();
		             }
	         }
		 }
		 catch(Exception e)
		 {
			  e.printStackTrace();
		 }
		 return strHostID;
	 }
	 
	 public int GetEmployerIDFromName ()
	 {
		 String strEmployerID = "";
		 int EmployerQSid = 0;
		 try
		 { 
			 PreparedStatement objSelectPS = null;
			 String strSqlSelectHostQSID = "";			 
			 if(strSelectedDatabaseType.equalsIgnoreCase("MSSQL"))
				 strSqlSelectHostQSID= "SELECT top 1 QSID, Name FROM EMPLOYERS WHERE NAME LIKE '%"+strEmployerAndHostName+"%';";
			 else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
					 strSqlSelectHostQSID= "SELECT QSID, Name FROM EMPLOYERS WHERE NAME = '"+strEmployerAndHostName+"'";
			 objSelectPS  = objConnection.prepareStatement(strSqlSelectHostQSID);
		     objResult = objSelectPS.executeQuery();
	         if(objResult.next())
	         {
	             if(objResult.getString("Name").equalsIgnoreCase(strEmployerAndHostName))
	             {
	            	 strEmployerID = objResult.getString("QSID");
		        	 if(!strEmployerID.isEmpty())
		        	 {
		        		 EmployerQSid=Integer.parseInt(strEmployerID);
		        		 objSelectPS.close();
		        	 }
	             }
	         }
		 }
		 catch(Exception e)
		 {
			  e.printStackTrace();
		 }
		 return EmployerQSid;
	 }

	 public boolean InsertIntoObjectImages ()
	 {
		 boolean bInsertedIntoImages = false;
		 int TotalNumberOfRecord = 0;
		 PreparedStatement ps = null;
		 FileInputStream fin = null;
		 try
		 { 
			 String strSqlPersons = "INSERT INTO OBJECTIMAGES (QSID, IMG)\n" + 
		 		  		"VALUES (?, ?)";
		 	 ps = objConnection.prepareStatement(strSqlPersons);
		 	 int nMaxObjectQSID = GetMaxObjectImagesQSID ();
		 	 TotalNumberOfRecord=Integer.parseInt(strNumberOfRecordsToBeCreated);
			 for (int i = 1; i <=TotalNumberOfRecord; i++) 
		 	 {
				 fin = new FileInputStream("./Test_Image.jpg");
				 nObjectImageQSID.add(nMaxObjectQSID+i);
		 		 ps.setString(1, Integer.toString(nMaxObjectQSID+i));
		 		 ps.setBinaryStream(2, fin);
	             ps.addBatch();
	 		  }
			  ps.executeBatch(); // insert remaining records
		 	  ps.close();
		 	  bInsertedIntoImages = true;
		 }
		 catch(Exception e)
		 {
			  e.printStackTrace();
		 }
		 return bInsertedIntoImages;
	 }
	 
	 public int GetMaxObjectImagesQSID ()
	 {
		 String strMaxValue = "";
		 int maxQSID;
		 try
		 { 
			 PreparedStatement objSelectPS = null;
			 String strSqlSelectMaxQSID = "";
			 if(strSelectedDatabaseType.equalsIgnoreCase("MSSQL"))
				 strSqlSelectMaxQSID = "SELECT isnull(max(QSID),0)+1 AS QSID FROM OBJECTIMAGES";
			 else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
				 strSqlSelectMaxQSID= "SELECT NVL(max(QSID),0)+1 AS QSID FROM OBJECTIMAGES";
			 objSelectPS  = this.objConnection.prepareStatement(strSqlSelectMaxQSID);
		     objResult = objSelectPS.executeQuery();
	         if(objResult.next())
	         {
	             strMaxValue = objResult.getString("QSID");
	             if(!strMaxValue.isEmpty())
	            	 objSelectPS.close();
	         }
		 }
		 catch(Exception e)
		 {
			  e.printStackTrace();
		 }
		 maxQSID=Integer.parseInt(strMaxValue);
		 return maxQSID;
	 }
	 
	 public static void saveFile(final String fileName, final String str) 
	 {    
		 try 
		 {
		      File f = new File(fileName);
		      if (new File(f.getParent()).exists() == false) 
		      {
		        f.getParentFile().mkdirs();
		      }
		      f.createNewFile();
		      objprintStream = new PrintStream(new FileOutputStream(f, false));
		      objprintStream.println(str);
		      objprintStream.close();
		    } 
		 catch (Exception e) 
		 {
		      e.printStackTrace();
		      System.err.println(fileName);
		 }
	}
	 
	 public static String getRandomString(int stringCount) 
		{    
		    char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		    StringBuilder sb = new StringBuilder();
		    Random random = new Random();
		    
		    for (int i = 0; i < stringCount; i++) {
		        char c = chars[random.nextInt(chars.length)];
		        sb.append(c);
		    }
		    return sb.toString();
		}
}
