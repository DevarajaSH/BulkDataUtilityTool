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

public class DatabaseTestUI_V06 {

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
	private static PrintStream objprintStream = null;
	public static ArrayList<Integer> nObjectImageQSID = new ArrayList<Integer>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					DatabaseTestUI_V06 window = new DatabaseTestUI_V06();
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
	public DatabaseTestUI_V06() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame("BulkData Insert Utility - v6.0");
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
		
		JRadioButton rdbtnHost = new JRadioButton("Host");
		
		ButtonGroup TableGroup = new ButtonGroup ();
		TableGroup.add (rdbtnPersons);
		TableGroup.add (rdbtnVisitors);
		TableGroup.add (rdbtnVisit);
		TableGroup.add (rdbtnHost);
		
		JLabel lblHostName = new JLabel("Host Name : ");
		lblHostName.setVisible(false);
		
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
					lblHostName.setVisible(true);
					lblHostName.setText("Host Name : ");
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
					lblHostName.setVisible(true);
					lblHostName.setText("Employer Name : ");
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
					lblHostName.setVisible(false);
					EmployerHostNametextField.setVisible(false);
				}
			}
		});
		
		rdbtnHost.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				strSelectedTableName = "Host";
				if(rdbtnHost.isSelected())
				{
					lblHostName.setVisible(false);
					EmployerHostNametextField.setVisible(false);
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
							if(DatabaseConnectivity ())
							{
								strEmployerAndHostName = EmployerHostNametextField.getText();
								if(InsertIntoPersonsTable ("Persons"))
								{
									ExecuteStoreProcedure ();
						   	 		objConnection.close();
								}
							}
						}
						else if(strSelectedTableName.equals("Visitors"))
						{
							if(DatabaseConnectivity ())
							{
								if(InsertIntoVisitorTable ())
								{
									ExecuteStoreProcedure ();
						   	 		objConnection.close();
								}
							}
						}
					} 
					catch (HeadlessException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
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
				String strText = " Bulk Data Tool Version : 5.0 "
						+ "\n Release date : 2019-07-02"
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
											.addComponent(rdbtnHost))))
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
										.addComponent(lblHostName))))
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
						.addComponent(rdbtnHost))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHostName)
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
			String strExecProcedure = "";
			if(strSelectedDatabaseType.equalsIgnoreCase("MSSQL"))
 		     strExecProcedure = "EXEC UPDATEOBJECTIDS";
			else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
			{
				strExecProcedure = "begin UPDATEOBJECTIDS; end;";
			}
	 		 PreparedStatement ps = objConnection.prepareStatement(strExecProcedure);
	 		 ps.execute();
	 		 //ps.execute
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
				 strSqlSelectMaxQSID= "SELECT isnull(max(QSID),0)+1 AS QSID FROM VISITORS";
			 else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
				 strSqlSelectMaxQSID= "SELECT NVL(max(QSID),0)+1 AS QSID FROM VISITORS";
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
		 			 String strFirstName = objfaker.name().firstName();
			 		 String strLastName =   objfaker.name().lastName();
			 		 String strEmailID = strFirstName+"."+strLastName+"@"+"test.com";		 		 
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
				 strSqlSelectMaxQSID= "SELECT isnull(max(QSID),0)+1 AS QSID FROM PERSONS";
			 else if (strSelectedDatabaseType.equalsIgnoreCase("Oracle"))
				 strSqlSelectMaxQSID= "SELECT NVL(max(QSID),0)+1 AS QSID FROM PERSONS";
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
			 {
				 //strSqlSelectHostQSID= "SELECT * FROM (SELECT QSID, Name FROM EMPLOYERS WHERE NAME LIKE '%"+strEmployerAndHostName+"%') WHERE rownum = 1;";
				 strSqlSelectHostQSID= "SELECT QSID, Name FROM EMPLOYERS WHERE NAME = '"+strEmployerAndHostName+"'";
			 }
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
}
