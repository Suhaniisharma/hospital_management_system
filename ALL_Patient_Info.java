package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ALL_Patient_Info extends JFrame {
    ALL_Patient_Info(){

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

// Create table
        JTable table = new JTable();
        table.setBackground(new Color(90, 156, 163));
        table.setFont(new Font("Tahoma", Font.BOLD, 12));

// Wrap the table inside a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 860, 450); // match within panel size
        panel.add(scrollPane);

        JButton button=new JButton("BACK");
        button.setBounds(350,500,120,30);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

// Load data into table
        try {
            Conn c = new Conn();
            String q = "SELECT * FROM Patient_Info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

// Frame setup
        setUndecorated(true);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);



    }
    public static void main(String[] args) {
        new ALL_Patient_Info();
    }
}
