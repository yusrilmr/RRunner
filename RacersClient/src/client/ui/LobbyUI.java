package client.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class LobbyUI extends JFrame {
	private DefaultTableModel model;
	JTextField TextFieldGameName;
	UIHandler uiHandler;
	JPanel inputPanel;

	private JTable table;

	public LobbyUI(UIHandler uiHandler) {
		super();
		this.uiHandler = uiHandler;
		this.addWindowListener(uiHandler);

		TextFieldGameName = new JTextField();
		TextFieldGameName.setColumns(10);

		model = new DefaultTableModel();
		model.addColumn("Title");
		model.addColumn("Capacity");
		model.addColumn("Host");
		model.addColumn("Status");
		model.addColumn("Action");

		table = new JTable(model);

		table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), uiHandler));
		table.getColumn("Action").setCellRenderer(new ButtonRenderer());

		JButton createButton = new JButton("Create");
		createButton.addActionListener(uiHandler);

		JButton addButton = new JButton("Refresh");
		addButton.addActionListener(uiHandler);

		JButton removeButton = new JButton("Logout");
		removeButton.addActionListener(uiHandler);

		inputPanel = new JPanel();
		inputPanel.add(addButton);
		inputPanel.add(removeButton);
		inputPanel.add(createButton);
		inputPanel.add(TextFieldGameName);

		Container container = getContentPane();
		container.add(new JScrollPane(table), BorderLayout.CENTER);
		container.add(inputPanel, BorderLayout.NORTH);

		setSize(500, 300);
	}

	public void addRow(String gameName, int amountPlayers, String host, String status, String join){
		String[] row = { gameName, Integer.toString(amountPlayers) + "/4", host, status, join};
		model.addRow(row);
	}

	public void clearRows(){
		model.setRowCount(0);
	}

	public String getTextFieldGameName(){
		return TextFieldGameName.getText();
	}

	public void sendError(String s){
		JOptionPane.showMessageDialog(null, s);
	}

	public JPanel getPanel(){
		return inputPanel;
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
		int row;
		JTable table;
		UIHandler uiHandler;

		public ButtonEditor(JCheckBox checkBox, UIHandler uiHandler) {
			super(checkBox);
			this.uiHandler = uiHandler;
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
				this.table = table;
				this.row = row;
			}
			label = (value == null) ? "" : value.toString();
			button.setText(label);
			isPushed = true;
			return button;
		}

		public Object getCellEditorValue() {
			if (isPushed) {
				String gameName = table.getValueAt(row, 0).toString();
				uiHandler.joinRoom(gameName);
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

}