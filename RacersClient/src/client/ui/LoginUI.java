package client.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginUI extends JFrame{
	UIHandler uiHandler;
	JLabel LabelGreeting;
	JLabel LabelUsername;
	JLabel LabelPassword;
	JLabel LabelIPAddress;
	JLabel LabelResponse;
	JTextField TextFieldUsername;
	JTextField TextFieldPassword;
	JButton ButtonSignIn;
	JButton ButtonExit;
	JButton ButtonSignUp;

	JButton BtnArrayOut;
	JButton BtnArith;
	JButton BtnNeg;
	JButton BtnNull;
	
	public LoginUI(UIHandler uiHandler){
		this.uiHandler = uiHandler;
		
		getContentPane().setLayout(null);
		setSize(500, 500);
		setTitle("RaceRunner - Sign In");
		
		LabelGreeting=new JLabel("Race Runner !!!");
		LabelGreeting.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelGreeting.setBounds(180, 20, 150, 50);
		
		LabelUsername=new JLabel("Username:");
		LabelUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelUsername.setBounds(80, 100, 300, 20);
		
		LabelPassword=new JLabel("Password:");
		LabelPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelPassword.setBounds(80, 130, 300, 20);
		
		LabelResponse=new JLabel("");
		LabelResponse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelResponse.setBounds(200, 200, 300, 20);
		
		TextFieldUsername=new JTextField();
		TextFieldUsername.setBounds(160, 100, 250, 20);
		
		TextFieldPassword=new JTextField();
		TextFieldPassword.setBounds(160, 130, 250, 20);
		
		ButtonSignIn=new JButton("Sign In");
		ButtonSignIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ButtonSignIn.setBounds(200, 245, 100, 35);
		ButtonSignIn.addActionListener(uiHandler);	
		
		ButtonSignUp=new JButton("Sign Up");
		ButtonSignUp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ButtonSignUp.setBounds(20, 400, 100, 35);
		ButtonSignUp.addActionListener(uiHandler);	
		
		ButtonExit=new JButton("Exit");
		ButtonExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ButtonExit.setBounds(370, 400, 100, 35);
		ButtonExit.addActionListener(uiHandler);	
		
		getContentPane().add(LabelGreeting);
		getContentPane().add(LabelUsername);
		getContentPane().add(LabelPassword);
		getContentPane().add(LabelResponse);
		getContentPane().add(TextFieldUsername);
		getContentPane().add(TextFieldPassword);
		getContentPane().add(ButtonSignIn);
		getContentPane().add(ButtonSignUp);
		getContentPane().add(ButtonExit);
	
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public String getUsername(){
		return TextFieldUsername.getText();
	}
	
	public String getPassword(){
		return TextFieldPassword.getText();
	}
	
	public void setResponse(String s){
		LabelResponse.setText(s);
	}
}
