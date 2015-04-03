package clientView;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginView extends JFrame {
	
	JLabel LabelGreeting;
	JLabel LabelUsername;
	JLabel LabelPassword;
	JLabel LabelIPAddress;
	JTextField TextFieldUsername;
	JTextField TextFieldPassword;
	JTextField TextFieldIPAddress;
	JButton ButtonForgotPassword;
	JButton ButtonSignIn;
	JButton ButtonExit;
	JButton ButtonSignUp;

	JButton BtnArrayOut;
	JButton BtnArith;
	JButton BtnNeg;
	JButton BtnNull;
	
	public LoginView(){
		
		forget a = new forget();
		signin b = new signin();
		signup c = new signup();
		exit d = new exit();
		
		/*Set ukuran ,jenis layout, title frame*/
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
		
		LabelIPAddress=new JLabel("IP Address:");
		LabelIPAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelIPAddress.setBounds(80, 160, 300, 20);
		
		TextFieldUsername=new JTextField();
		TextFieldUsername.setBounds(160, 100, 250, 20);
		
		TextFieldPassword=new JTextField();
		TextFieldPassword.setBounds(160, 130, 250, 20);
		
		TextFieldIPAddress=new JTextField();
		TextFieldIPAddress.setBounds(160, 160, 250, 20);
		
		ButtonForgotPassword=new JButton("Forgot Password?");
		ButtonForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ButtonForgotPassword.setBounds(165, 190, 175, 35);
		ButtonForgotPassword.addActionListener(a);	
		
		ButtonSignIn=new JButton("Sign In");
		ButtonSignIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ButtonSignIn.setBounds(200, 245, 100, 35);
		ButtonSignIn.addActionListener(b);	
		
		ButtonSignUp=new JButton("Sign Up");
		ButtonSignUp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ButtonSignUp.setBounds(20, 400, 100, 35);
		ButtonSignUp.addActionListener(c);	
		
		ButtonExit=new JButton("Exit");
		ButtonExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ButtonExit.setBounds(370, 400, 100, 35);
		ButtonExit.addActionListener(d);	
		
		/*Tambahkan semua elemen*/
		getContentPane().add(LabelGreeting);
		getContentPane().add(LabelUsername);
		getContentPane().add(LabelPassword);
		getContentPane().add(LabelIPAddress);
		getContentPane().add(TextFieldUsername);
		getContentPane().add(TextFieldPassword);
		getContentPane().add(TextFieldIPAddress);
		getContentPane().add(ButtonForgotPassword);
		getContentPane().add(ButtonSignIn);
		getContentPane().add(ButtonSignUp);
		getContentPane().add(ButtonExit);
		

	
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		LoginView x =new LoginView();
	}
	
	/*Action Listener*/
	class forget implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==ButtonForgotPassword){
				try{ 
					JOptionPane masuk = new JOptionPane();
					int a = Integer.valueOf(masuk.showInputDialog("Masukan angka pertama"));
					int b = Integer.valueOf(masuk.showInputDialog("Masukan angka pembagi pertama"));
					int c = a/b;
					JOptionPane.showMessageDialog(null, "Perhitungan Dapat Dilakukan");
				}catch(ArithmeticException err){
					JOptionPane.showMessageDialog(null, "Syntax Error");
				}
			}
		}
	}
	
	class signin implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if ((TextFieldUsername.equals(ArrayListUsername)) && (TextFieldPassword.equals(ArrayListPassword))) {
				LobbyView lb = new LobbyView();
				lb.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "ERROR", "Your Username & Password did not match", JOptionPane.ERROR_MESSAGE);
				}
	}
	}
	
	class signup implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			SignUpView su = new SignUpView();
			su.setVisible(true);
		}
	}
	
	class exit implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==BtnNull){
				try {
					List list = null;
					list.add("asa");
					JOptionPane.showMessageDialog(null, "yes update");
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "no update");
				}
			}
		}
	}

	

}
