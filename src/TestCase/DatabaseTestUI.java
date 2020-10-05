package TestCase;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.swing.JTextField;
import java.awt.HeadlessException;
import java.awt.Component;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.github.javafaker.Faker;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import java.awt.Font;

public class DatabaseTestUI {

	private JFrame frame;
	
	private static Connection objConnection = null;
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
	private static PrintStream objprintStream = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					DatabaseTestUI window = new DatabaseTestUI();
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
	 * @throws SQLException 
	 */
	public DatabaseTestUI() throws SQLException 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame("SAFE TestData Utility - v3.0");
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
		
		JLabel lblNumberOfRecordsToCreate = new JLabel("No. of records to Create");
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
		rdbtnPersons.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				strSelectedTableName = "Persons";
			}
		});
		
		JRadioButton rdbtnVisitors = new JRadioButton("Visitors");
		rdbtnVisitors.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				strSelectedTableName = "Visitors";
			}
		});
		ButtonGroup TableGroup = new ButtonGroup ();
		TableGroup.add (rdbtnPersons);
		TableGroup.add (rdbtnVisitors);
		
		JLabel lblDatabaseType = new JLabel("Database Type : ");
		
		JLabel lblTableToPopulate = new JLabel("Table to Populate : ");
		
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
							DatabaseUserName.getText().isEmpty() || passwordField.getText().isEmpty() || strSelectedDatabaseType.isEmpty())
						JOptionPane.showMessageDialog(null, "Please enter all the fields");
					else
					{
						strDataBaseServer = DatabaseServerIP.getText(); 
						strDataBaseName = DatabaseName.getText(); 
						strDataBaseUserName = DatabaseUserName.getText(); 
						strDataBasePassword = passwordField.getText(); 
						strNumberOfRecordsToBeCreated = NumberOfRecordsToCreate.getText();
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
					try 
					{
						if(objConnection.isClosed())
							DatabaseConnectivity ();
						if (strSelectedDatabaseType == null)
							JOptionPane.showMessageDialog(null, "Please select Database type !");
						else if (strSelectedTableName == null)
							JOptionPane.showMessageDialog(null, "Please select the table to Insert data !");
						else if(strSelectedTableName.equals("Persons"))
						{
							if(InsertIntoPersonsTable ())
							{
								ExecuteStoreProcedure ();
							}
						}
						else if(strSelectedTableName.equals("Visitors"))
						{
							if(InsertIntoVisitorTable ())
							{
								ExecuteStoreProcedure ();
							}
						}
					} 
					catch (HeadlessException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}          
		}); 
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 211, 550, 187);
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
				String strText = " SAFE TestData Utility : 3.0 "
						+ "\n Release date : 2019-05-02"
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
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addComponent(btnClear)
							.addGap(18)
							.addComponent(btnTestDBConnection)
							.addGap(18)
							.addComponent(btnRunOrSubmit)
							.addGap(10)
							.addComponent(btnClose))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDBServer)
								.addComponent(lblDBName))
							.addGap(62)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(DatabaseServerIP, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
								.addComponent(DatabaseName, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDBPassword)
										.addComponent(lblNumberOfRecordsToCreate))
									.addGap(68)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(NumberOfRecordsToCreate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(92))
										.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
								.addComponent(scrollPane, Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTableToPopulate)
										.addComponent(lblDatabaseType))
									.addGap(108)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnOracle)
										.addComponent(rdbtnVisitors))
									.addGap(50)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnMSSQL)
										.addComponent(rdbtnPersons)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addComponent(lblDBUsername)
							.addGap(68)
							.addComponent(DatabaseUserName, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
					.addGap(112))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(438, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(43)
									.addComponent(lblDBServer))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(41)
									.addComponent(DatabaseServerIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDBName)
								.addComponent(DatabaseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDBUsername)
								.addComponent(DatabaseUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDBPassword)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNumberOfRecordsToCreate)
								.addComponent(NumberOfRecordsToCreate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(rdbtnMSSQL)
										.addComponent(rdbtnOracle))
									.addGap(14))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDatabaseType)
									.addGap(18)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnPersons)
								.addComponent(rdbtnVisitors)
								.addComponent(lblTableToPopulate))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnClear)
								.addComponent(btnTestDBConnection)
								.addComponent(btnRunOrSubmit)
								.addComponent(btnClose))
							.addGap(33)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		//action listener
  }
	
	public boolean DatabaseConnectivity () throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException  
	{
		 boolean bDatabaseConnect = false;
		  try
		  {	
			  if(objConnection == null)
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
					  String strURL = "jdbc:oracle:thin:@192.168.6.59:1521/xe";	
					  objConnection = DriverManager.getConnection(strURL, strDataBaseUserName, strDataBasePassword);
					  objStatement = objConnection.createStatement();
					  bDatabaseConnect = true;
					  System.out.println("Database Connection is Sucessful"); 
				  }
				  else if(strSelectedDatabaseType.isEmpty())
					  System.out.println("Unable to connect Database");  
			  }
			  else if (objConnection != null)
			  {
				  bDatabaseConnect=true;
				  System.out.println("Database connection is successful");  
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
 		     String strSqlVisitor = "INSERT INTO dbo.VISITORS (QSID,COMPANY, COUNTRY, EMAIL, FIRSTNAME, LASTNAME, OWNERID, PHONE,WATCHLIST,VISITORTYPE)\n" + 
	 		  		"VALUES (?,?, ?, ?, ?, ?, ?, ?,?,?)";
	 		 PreparedStatement ps = objConnection.prepareStatement(strSqlVisitor);
	 		 Faker objfaker = new Faker();
	 		 int strMaxVisitorQSID = GetMaxVisitorQSID ();
	 		 int TotalNumberOfRecord=Integer.parseInt(strNumberOfRecordsToBeCreated);
	 		 for (int i = 1; i <=TotalNumberOfRecord; i++) 
	 		 {
				String strFirstName = objfaker.name().firstName();
				String strLastName =   objfaker.name().lastName();
				String strEmailID = strFirstName+"."+strLastName+"@"+"test.com"; 
				System.out.println("Visitor Created : "+" " +i + " "+strFirstName+ " "+strLastName);
				ps.setString(1, Integer.toString(strMaxVisitorQSID+i));
				ps.setString(2, objfaker.company().name());
				ps.setString(3, objfaker.address().country());
				ps.setString(4, strEmailID);
				ps.setString(5, strFirstName);
				ps.setString(6, strLastName);					
				ps.setString(7, "2");
				ps.setString(8, objfaker.phoneNumber().cellPhone());
				ps.setString(9, "0");
				ps.setString(10, "Visitor");
				ps.addBatch();
	 		}
	 		ps.executeBatch(); // insert remaining records
	 		ps.close();
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
			e.printStackTrace();
		}
		return bVisitorCreated;
	}
	
	public void ExecuteStoreProcedure ()
	{
		try
		{
 		     String strExecProcedure = "EXEC UPDATEOBJECTIDS";
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
			 PreparedStatement objSelectPS = null;
			 String strSqlSelectMaxQSID = "SELECT isnull(max(QSID),0)+1 AS QSID FROM VISITORS";
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
	
	public boolean InsertIntoPersonsTable () throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException  
	  {
		boolean bPersonInsert = false;
		  try
		  {
			  System.out.println("Inserting data into Person table");
			  LocalDateTime objCurrentTimeBeforeInsert = LocalDateTime.now();
	 		  String strSqlVisitor = "INSERT INTO dbo.PERSONS (QSID,FIRSTNAME,LASTNAME, NAME, TYPE, STATUS, USERNAME,PRIMARYID,VISITORCANINV,VISITORESCRIN,IS_SELFSERVICE_USER,EMAIL)\n" + 
	 		  		"VALUES (?,?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
	 		  PreparedStatement ps = objConnection.prepareStatement(strSqlVisitor);
	 		  Faker objfaker = new Faker();
	 		  int strMaxPersonsQSID = GetMaxPersonsQSID ();
	 		  String strMaxPrimaryID=GetMaxPrimaryID();
	 		  int pid=Integer.parseInt(strMaxPrimaryID.substring(2));
	 		  pid++;
	 		  int TotalNumberOfRecord=Integer.parseInt(strNumberOfRecordsToBeCreated);
	 		  for (int i = 1; i <=TotalNumberOfRecord; i++) 
	 		  {
	 			 String strFirstName = objfaker.name().firstName();
		 		 String strLastName =   objfaker.name().lastName();
		 		 String strEmailID = strFirstName+"."+strLastName+"@"+"test.com";		 		 
		 		 System.out.println("Persons Created : "+" " +i + " "+strFirstName+ " "+strLastName);
		 		 ps.setString(1, Integer.toString(strMaxPersonsQSID+i));
		 		 ps.setString(2,  strFirstName);
		 		 ps.setString(3, strLastName);
	 			 ps.setString(4, strFirstName + " "+ strLastName);					
	 			 ps.setString(5, "3");
	 			 ps.setString(6, "Active");
	 			 ps.setString(7, "");		
	 			 ps.setString(8, "NE"+Integer.toString(pid+i));
	 			 ps.setString(9, "1");
	 			 ps.setString(10, "1");
	 			 ps.setString(11, "0");
	 			 ps.setString(12, strEmailID);
	             ps.addBatch();
	             if (i % 10000 == 0) 
				 {
					 int[] ncount = ps.executeBatch();  // Execute every 1000 items.
					 System.out.println("ncount.length : "+ncount.length);
					 ps.clearBatch();
		         }
	 		  }
	 		 ps.executeBatch(); // insert remaining records
	 		 ps.close();
	 		 bPersonInsert = true;
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
			 String strSqlSelectMaxQSID = "SELECT isnull(max(QSID),0)+1 AS QSID FROM PERSONS";
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
		 }
		 catch(Exception e)
		 {
			  e.printStackTrace();
		 }
		 return strMaxPrimaryID;
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
}
