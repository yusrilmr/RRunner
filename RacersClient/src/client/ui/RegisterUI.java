package client.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterUI extends JFrame{
	UIHandler uiHandler;
	JLabel LabelTitle;
	JLabel LabelUsername;
	JLabel LabelPassword;
	JLabel LabelName;
	JLabel LabelResponse;
	JButton ButtonSignUp;
	JTextField TextFieldUsername;
	JTextField TextFieldPassword;
	JTextField TextFieldName;
	
	public RegisterUI(UIHandler uiHandler){
		this.uiHandler = uiHandler;
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
		
		TextFieldPassword = new JTextField();
		TextFieldPassword.setBounds(250, 160, 125, 25);
				
		LabelName=new JLabel("Name");
		LabelName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelName.setBounds(100, 230, 125, 25);
		
		TextFieldName = new JTextField();
		TextFieldName.setBounds(250, 230, 125, 25);
		
		LabelResponse=new JLabel("");
		LabelResponse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelResponse.setBounds(100, 280, 300, 20);
		
		ButtonSignUp=new JButton("Sign Up");
		ButtonSignUp.setBounds(125,350,250,50);
		ButtonSignUp.addActionListener(uiHandler);
		
		/*Add the Element*/
		getContentPane().add(LabelTitle);
		getContentPane().add(LabelUsername);
		getContentPane().add(TextFieldUsername);
		getContentPane().add(LabelPassword);
		getContentPane().add(TextFieldPassword);
		getContentPane().add(LabelResponse);
		//getContentPane().add(LabelName);
		//getContentPane().add(TextFieldName);
		getContentPane().add(ButtonSignUp);
		
		setLocationRelativeTo(null);
	}
	
	public void setResponse(String s){
		LabelResponse.setText(s);
	}
	
	public String getUsername(){
		return TextFieldUsername.getText();
	}
	
	public String getPassword(){
		return TextFieldPassword.getText();
	}
}
