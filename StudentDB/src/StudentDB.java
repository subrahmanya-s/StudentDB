import java.awt.EventQueue;
import java.sql.*; 
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

public class StudentDB {

	private JFrame frame;
	private JTextField txtUSN;
	private JTextField txtName;
	private JTextField txtBranch;
	private JTextField txtSem;
	private JTable table;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDB window = new StudentDB();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentDB() {
		initialize();
		Connect();
	
	}


	Connection con;
	PreparedStatement pst;
	ResultSet rs;

 
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root","");
	        	JOptionPane.showMessageDialog(null, "Connection Made");

	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	ex.printStackTrace();
	        	JOptionPane.showMessageDialog(null, "Connection Error");
				
	        	  
	        }
 
	    }
	
	
			 
	 
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 165, 0));
		frame.setBounds(100, 100, 963, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUSN = new JTextField();
		txtUSN.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txtUSN.setForeground(new Color(255, 255, 255));
		txtUSN.setBackground(new Color(255, 165, 0));
		txtUSN.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtUSN.setBounds(678, 116, 158, 19);
		frame.getContentPane().add(txtUSN);
		txtUSN.setColumns(10);
		
		txtName = new JTextField();
		txtName.setForeground(new Color(255, 255, 255));
		txtName.setBackground(new Color(255, 165, 0));
		txtName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txtName.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtName.setColumns(10);
		txtName.setBounds(678, 168, 158, 19);
		frame.getContentPane().add(txtName);
		
		txtBranch = new JTextField();
		txtBranch.setForeground(new Color(255, 255, 255));
		txtBranch.setBackground(new Color(255, 165, 0));
		txtBranch.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txtBranch.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtBranch.setColumns(10);
		txtBranch.setBounds(678, 227, 158, 19);
		frame.getContentPane().add(txtBranch);
		
		txtSem = new JTextField();
		txtSem.setForeground(new Color(255, 255, 255));
		txtSem.setBackground(new Color(255, 165, 0));
		txtSem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txtSem.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtSem.setColumns(10);
		txtSem.setBounds(678, 295, 158, 19);
		frame.getContentPane().add(txtSem);
		
		
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.setForeground(new Color(255, 69, 0));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Do you really want to exit ?", "Student Data Management System", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
				
					System.exit(0);
				}
				
			}
		});
		btnExit.setBounds(829, 533, 95, 38);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Reset");
		btnClear.setBackground(new Color(255, 255, 255));
		btnClear.setForeground(new Color(255, 69, 0));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtUSN.setText(null);
				txtName.setText(null);
				txtBranch.setText(null);
				txtSem.setText(null);
				

				
			}
		});
		btnClear.setBounds(573, 533, 95, 38);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(255, 165, 0));
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setBounds(10, 97, 543, 340);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"USN", "Name", "Branch", "Sem"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 165, 0));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBounds(571, 432, 353, 88);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("USN");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(35, 36, 67, 28);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
		            String id = txtSearch.getText();

		                pst = con.prepareStatement("select USN,Name,Branch,Sem from studentdb where USN = ?");
		                pst.setString(1,id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		                String USN = rs.getString(1);
		                String Name = rs.getString(2);
		                String Branch = rs.getString(3);
		                String Sem = rs.getString(4);

		                txtUSN.setText(USN);
		                txtName.setText(Name);
		                txtBranch.setText(Branch);
		                txtSem.setText(Sem);
		            }  
		            
		            else
		            {
		            	txtUSN.setText("");
		            	txtName.setText("");
		                txtBranch.setText("");
		                txtSem.setText("");
   
		            }
		        } 
			
			 catch (SQLException ex) {
		           
		        }
			}
		});
		txtSearch.setBounds(112, 36, 165, 28);
		txtSearch.setColumns(10);
		panel.add(txtSearch);
		
		JButton btnNewButton_2_1 = new JButton("Update");
		btnNewButton_2_1.setBackground(new Color(255, 255, 255));
		btnNewButton_2_1.setForeground(new Color(255, 69, 0));
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String USN,Name,Branch,Sem;
				
				USN = txtUSN.getText();
				Name = txtName.getText();
				Branch = txtBranch.getText();
				Sem  = txtSem.getText();
				
				 try {
						pst = con.prepareStatement("update studentdb set Name='"+Name+"',Branch='"+Branch+"',Sem='"+Sem+"' where USN=?");
						pst.setString(1, USN);
			            
			            
			            pst.executeUpdate();
			            
			            JOptionPane.showMessageDialog(null, "Records Updated");
			         
			            txtUSN.setText("");
			            txtName.setText("");
			            txtBranch.setText("");
			            txtSem.setText("");
			           
					}
 
		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
			}
		});
		btnNewButton_2_1.setBounds(707, 371, 95, 38);
		frame.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Delete");
		btnNewButton_2_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2_2.setForeground(new Color(255, 69, 0));
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                        String id1;
                        id1  = txtSearch.getText();
			
			 try {
					pst = con.prepareStatement("delete from studentdb where USN =?");
			
		            pst.setString(1, id1);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Deleted");
		            
		           
		            txtUSN.setText("");
		            txtName.setText("");
		            txtBranch.setText("");
		            txtSem.setText("");
				}
 
	            catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2_2.setBounds(829, 371, 95, 38);
		frame.getContentPane().add(btnNewButton_2_2);
		
		JButton btnAddNew = new JButton("New");
		btnAddNew.setBackground(new Color(255, 255, 255));
		btnAddNew.setForeground(new Color(255, 69, 0));
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String sql = "INSERT INTO studentdb VALUES(?,?,?,?)";
				
				try {
						pst = con.prepareStatement(sql);
						pst.setString(1, txtUSN.getText());
						pst.setString(2, txtName.getText());
						pst.setString(3, txtBranch.getText());
						pst.setString(4, txtSem.getText());
					
						pst.executeUpdate();
							
					}
				catch(Exception ev) {
					JOptionPane.showMessageDialog(null, "New Record added");
					}
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						
						txtUSN.getText(),
						txtName.getText(),
						txtBranch.getText(),
						txtSem.getText()
						
				});
				
				
				
			}
		});
		btnAddNew.setBounds(572, 371, 95, 38);
		frame.getContentPane().add(btnAddNew);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 240));
		panel_2.setBounds(0, 0, 963, 79);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Student Data Managament System");
		lblNewLabel_2.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 165, 0)));
		lblNewLabel_2.setBounds(148, 10, 681, 59);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(255, 165, 0));
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setFont(new Font("High Tower Text", Font.BOLD, 44));
		Image img = new ImageIcon(this.getClass().getResource("/nmit.png")).getImage();
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 165, 0));
		panel_1.setBounds(559, 65, 404, 516);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setBounds(26, 162, 80, 25);
		panel_1.add(lblBranch);
		lblBranch.setForeground(new Color(255, 255, 255));
		lblBranch.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblSem = new JLabel("Sem");
		lblSem.setBounds(26, 233, 80, 16);
		panel_1.add(lblSem);
		lblSem.setForeground(new Color(255, 255, 255));
		lblSem.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(26, 99, 95, 25);
		panel_1.add(lblName);
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
		JLabel lblNewLabel = new JLabel("USN");
		lblNewLabel.setBounds(26, 56, 75, 16);
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
		frame.setUndecorated(true);
	}
}
