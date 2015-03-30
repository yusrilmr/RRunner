package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp extends JFrame{
	JLabel LabelTitle;
	JLabel LabelUsername;
	JLabel LabelPassword;
	JLabel LabelRepeatPassword;
	JLabel LabelName;
	JButton ButtonSignUp;
	JTextField TextFieldUsername;
	JTextField TextFieldName;
	JPasswordField PasswordFieldPassword;
	JPasswordField PasswordFieldRepeatPassword;
	
	public SignUp(){
		
		registerSignUp RSU = new registerSignUp();
		
		getContentPane().setLayout(null);
		setSize(500, 500);
		setTitle("RaceRunner - Sign Up");
		
		LabelTitle = new JLabel("Race Runner !!!");
		LabelTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		LabelTitle.setBounds(120, 23, 345, 50);
		
		LabelUsername=new JLabel("Username");
		LabelUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelUsername.setBounds(100, 125, 125, 25);
		
		TextFieldUsername = new JTextField();
		TextFieldUsername.setBounds(250, 125, 125, 25);
		
		LabelPassword=new JLabel("Password");
		LabelPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelPassword.setBounds(100, 160, 125, 25);
		
		PasswordFieldPassword = new JPasswordField();
		PasswordFieldPassword.setBounds(250, 160, 125, 25);
		
		LabelRepeatPassword=new JLabel("Repeat");
		LabelRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelRepeatPassword.setBounds(100, 195, 200, 25);
		
		PasswordFieldRepeatPassword = new JPasswordField();
		PasswordFieldRepeatPassword.setBounds(250, 195, 125, 25);
		
		LabelName=new JLabel("Name");
		LabelName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelName.setBounds(100, 230, 125, 25);
		
		TextFieldName = new JTextField();
		TextFieldName.setBounds(250, 230, 125, 25);
		
		ButtonSignUp=new JButton("Sign Up");
		ButtonSignUp.setBounds(125,350,250,50);
		ButtonSignUp.addActionListener(RSU);
		
		/*Add the Element*/
		getContentPane().add(LabelTitle);
		getContentPane().add(LabelUsername);
		getContentPane().add(TextFieldUsername);
		getContentPane().add(LabelPassword);
		getContentPane().add(PasswordFieldPassword);
		getContentPane().add(LabelRepeatPassword);
		getContentPane().add(PasswordFieldRepeatPassword);
		getContentPane().add(LabelName);
		getContentPane().add(TextFieldName);
		getContentPane().add(ButtonSignUp);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SignUp signup = new SignUp();
	}
	
	class registerSignUp implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==ButtonSignUp){
				if(Arrays.equals(PasswordFieldPassword.getPassword(),PasswordFieldRepeatPassword.getPassword())){
					String Username = LabelUsername.getText();
					String Name = "";
				}else{
					JOptionPane msg = new JOptionPane();
					msg.showMessageDialog(null, "Please Correct the Repeat Password", "Error",JOptionPane.ERROR_MESSAGE);
				}
				}
			}
		}
	}
