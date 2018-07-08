package gpa;

import java.util.Map;
import java.util.HashMap;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class GPA {
	private final static Map<String, Double> table = new HashMap<>() {{
		put("A+", 4.3); put("A", 4.0); put("A-", 3.7);
		put("B+", 3.3); put("B", 3.0); put("B-", 2.7);
		put("C+", 2.3); put("C", 2.0); put("C-", 1.7);
		put("F", 0.0);
	}};

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("GPA");
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel label1 = new JLabel("Database:");
		label1.setFont(new Font("Helvetica", Font.PLAIN, 14));

		JTextField field1 = new JTextField(16);
		field1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		field1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

		JLabel label2 = new JLabel("Query:");
		label2.setFont(new Font("Helvetica", Font.PLAIN, 14));

		JTextField field2 = new JTextField(16);
		field2.setFont(new Font("Helvetica", Font.PLAIN, 14));
		field2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

		JLabel label3 = new JLabel("A+ GPA:");
		label3.setFont(new Font("Helvetica", Font.PLAIN, 14));

		JTextField field3 = new JTextField(16);
		field3.setFont(new Font("Helvetica", Font.PLAIN, 14));
		field3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

		JLabel area = new JLabel();
		area.setFont(new Font("Helvetica", Font.PLAIN, 14));

		JButton button = new JButton("Calculate", null);
		button.setFont(new Font("Helvetica", Font.PLAIN, 14));
		button.setMargin(new Insets(4, 0, 0, 0));

		button.addActionListener((event) -> {
			try {
				String url = "jdbc:sqlite:" + field1.getText();
				Connection con = DriverManager.getConnection(url);
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(field2.getText());
				table.put("A+", Double.parseDouble(field3.getText()));
				double sum = 0;
				int credits = 0;
				while (rs.next()) {
					String grades = rs.getString("成績");
					if (!table.keySet().contains(grades))
						continue;
					sum += table.get(grades) * rs.getInt("學分");
					credits += rs.getInt("學分");
				}
				area.setText(Double.toString(sum / credits));
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});

		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(label1)
				.addComponent(label2)
				.addComponent(label3)
				.addComponent(button))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(field1)
				.addComponent(field2)
				.addComponent(field3)
				.addComponent(area)));

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(label1)
				.addComponent(field1))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(label2)
				.addComponent(field2))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(label3)
				.addComponent(field3))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(button)
				.addComponent(area)));

		frame.pack();
		frame.setResizable(false);
		frame.setSize(500, 150);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
