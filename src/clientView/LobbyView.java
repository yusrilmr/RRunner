package clientView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class LobbyView extends JFrame {
	private DefaultTableModel model;

	private JTable table;

	public LobbyView() {
		super();
		model = new DefaultTableModel();
		model.addColumn("Title");
		model.addColumn("Capacity");
		model.addColumn("Host");
		model.addColumn("Status");
		model.addColumn("Action");

		String[] row1 = { "Go!", "4/4", "John","Full","-" };
		model.addRow(row1);

		String[] row2 = { "Enter", "3/4", "Teun","Wait","Join" };
		model.addRow(row2);

		String[] row3 = { "Fair Play", "4/4", "Yusril","Full","-" };
		model.addRow(row3);

		String[] row4 = { "Join with us", "1/4", "Joey","Wait","Join" };
		model.addRow(row4);

		String[] row5 = { "wow", "2/4", "Ghani","Wait","Join" };
		model.addRow(row5);

		String[] row6 = { "wew", "4/4", "Yusuf","Full","-" };
		model.addRow(row6);

		String[] row7 = { "Room GG", "4/4", "wow","Full","-" };
		model.addRow(row7);

		table = new JTable(model);
		
		table.getColumn("Action").setCellRenderer(new ButtonRenderer());
	    table.getColumn("Action").setCellEditor(
	        new ButtonEditor(new JCheckBox()));

		JButton addButton = new JButton("Refresh");
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				//action
			}
		});

		JButton removeButton = new JButton("Logout");

		removeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				//action
			}
		});
		
		
		
		JPanel inputPanel = new JPanel();
		inputPanel.add(addButton);
		inputPanel.add(removeButton);

		Container container = getContentPane();
		container.add(new JScrollPane(table), BorderLayout.CENTER);
		container.add(inputPanel, BorderLayout.NORTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);
	}
	
	
	

	public static void main(String args[]) {
		new LobbyView();
	}
	
}

class ButtonRenderer extends JButton implements TableCellRenderer {

	  public ButtonRenderer() {
	    setOpaque(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else {
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}

class ButtonEditor extends DefaultCellEditor {
	  protected JButton button;

	  private String label;

	  private boolean isPushed;

	  public ButtonEditor(JCheckBox checkBox) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        fireEditingStopped();
	      }
	    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else {
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    label = (value == null) ? "" : value.toString();
	    button.setText(label);
	    isPushed = true;
	    return button;
	  }

	  public Object getCellEditorValue() {
	    if (isPushed) {
	      // 
	      // 
	      JOptionPane.showMessageDialog(button, label + ": Ouch!");
	      // System.out.println(label + ": Ouch!");
	    }
	    isPushed = false;
	    return new String(label);
	  }

	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	}
