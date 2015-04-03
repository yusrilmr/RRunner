package clientView;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LobbyView extends JFrame {
	DefaultTableModel model = new DefaultTableModel(new Object[][] {
			{ "some", "text" }, { "any", "text" }, { "even", "more" },
		      { "text", "strings" }, { "and", "other" }, { "text", "values" } },
	new Object[]{"Column 1", "Column 2"});
	
	public LobbyView(){
		setSize(500, 500);
		JTable table = new JTable(model);
		getContentPane().setLayout(null);
		
		table.setBounds(120, 23, 345, 50);
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		LobbyView lobby = new LobbyView();
	}
}
