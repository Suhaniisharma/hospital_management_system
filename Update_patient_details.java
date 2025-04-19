package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_patient_details extends JFrame {
    Update_patient_details(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,940,490);
        panel.setLayout(null);
        panel.setBackground(new Color(90,156,163));
        add(panel);

        JLabel label1= new JLabel("Update Patient Details");
        label1.setBounds(124,11,260,25);
        label1.setFont(new Font("Tahoma",Font.BOLD,20));
        label1.setForeground(Color.white);
        panel.add(label1);

        JLabel label2= new JLabel("Name");
        label2.setBounds(25,88,100,14);
        label2.setFont(new Font("Tahoma",Font.PLAIN,14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248,85,140,25);
        panel.add(choice);

        try{
            Conn c = new Conn();
            ResultSet resultSet=c.statement.executeQuery("select * from Patient_Info");
            while(resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel label3= new JLabel("Room Number :");
        label3.setBounds(25,129,100,14);
        label3.setFont(new Font("Tahoma",Font.PLAIN,14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JTextField textFieldR= new JTextField();
        textFieldR.setBounds(248,129,140,20);
        panel.add(textFieldR);

        JLabel label4= new JLabel("In-Time :");
        label4.setBounds(25,174,100,14);
        label4.setFont(new Font("Tahoma",Font.PLAIN,14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textFieldINTIME= new JTextField();
        textFieldINTIME.setBounds(248,174,140,20);
        panel.add(textFieldINTIME);

        JLabel label5= new JLabel("Amount Paid (Rs) :");
        label5.setBounds(25,216,150,14);
        label5.setFont(new Font("Tahoma",Font.PLAIN,14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textFieldAmount= new JTextField();
        textFieldAmount.setBounds(248,216,140,20);
        panel.add(textFieldAmount);

        JLabel label6= new JLabel("Pending Amount (Rs) :");
        label6.setBounds(25,261,150,14);
        label6.setFont(new Font("Tahoma",Font.PLAIN,14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textFieldPending = new JTextField();
        textFieldPending.setBounds(248,261,140,20);
        panel.add(textFieldPending);


        JButton Check= new JButton("CHECK");
        Check.setBounds(281,350,89,23);
        Check.setBackground(Color.BLACK);
        Check.setForeground(Color.white);
        panel.add(Check);
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = choice.getSelectedItem();
                String q = "select * from Patient_Info where Name = '"+id+"'";
                try{
                    Conn c = new Conn();
                    ResultSet resultSet= c.statement.executeQuery(q);
                    while(resultSet.next()){
                        textFieldR.setText(resultSet.getString("Room_Number"));
                        textFieldINTIME.setText(resultSet.getString("Time"));
                        textFieldAmount.setText(resultSet.getString("Deposite"));

                    }
                    ResultSet resultSet1= c.statement.executeQuery("select * from room where room_no = '"+textFieldR.getText()+"'");
                    while(resultSet1.next()){
                        String price = resultSet1.getString("Price");
                        int amountPaid= Integer.parseInt(price) - Integer.parseInt(textFieldAmount.getText());
                        textFieldPending.setText(""+amountPaid);
                    }

                }catch(Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton Update= new JButton("UPDATE");
        Update.setBounds(56,350,89,23);
        Update.setBackground(Color.BLACK);
        Update.setForeground(Color.white);
        panel.add(Update);
        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Conn c=new Conn();
                    String q = choice.getSelectedItem();
                    String room = textFieldR.getText();
                    String time = textFieldINTIME.getText();
                    String amount = textFieldAmount.getText();
                    c.statement.executeUpdate("update Patient_Info set Room_Number = '"+room+"', Time = '"+time+"', Deposite = '"+amount+"' where name = '"+q+"'");
                    JOptionPane.showMessageDialog(null,"Updated Successfully");
                    setVisible(false);


                }catch(Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back= new JButton("BACK");
        back.setBounds(168,350,89,23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        setUndecorated(true);
        setSize(800,400);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        new Update_patient_details();


    }
}
