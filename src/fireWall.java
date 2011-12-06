// Copyright (c) 2011 Rasika Bindoo
//
// Java iptables control GUI
//
// This code is available under the "MIT License". Please
// see the file COPYING in this distribution for license
// details.
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class fireWall extends Frame implements WindowListener,ActionListener {
        TextField text = new TextField(20);
        Label singleIP = new Label("Single IP Settings");
        Label blank = new Label("                                             ");
        Label blank2 = new Label("                                             ");
        Label zone = new Label("Zone Settings");
        Button b;
        Button b2;
        Button b3;
        Button blockAll;
        Button unblockAll;
        private JList list;
        private Button fireButton;
        private Button removeIP;
        TextField employeeName = new TextField(20) ;
       //String[] data = {"one", "two", "three", "four"};
        private DefaultListModel listModel;
        //JList dataList = new JList(data);
        private int numClicks = 0;

        public static void main(String[] args) {
                fireWall myWindow = new fireWall("Firewall");
                myWindow.setSize(350,300);
                myWindow.setVisible(true);
        }

        public fireWall(String title) {

                super(title);
                
                setLayout(new FlowLayout());
                addWindowListener(this);
                b = new Button("Block IP");
                b2 = new Button("Unblock IP");
                b3 = new Button("Add IP");
                blockAll = new Button("Block All");
                unblockAll =  new Button("Unblock All");
                removeIP = new Button("Remove IP");
                listModel = new DefaultListModel();
                listModel.addElement("98.139.180.149");
                //listModel.addElement("John Smith");
                //listModel.addElement("Kathy Green");
                list = new JList(listModel);
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list.setSelectedIndex(0);
                //list.addListSelectionListener(this);
                list.setVisibleRowCount(5);
                JScrollPane listScrollPane = new JScrollPane(list);
                //employeeName = new JTextField(10);
                add(singleIP);
                add(blank);
                add(b);
                add(b2);
                add(text);
                add(zone);
                add(blank2);
                add(b3);
                add(removeIP);
                add(employeeName);
                add(blockAll);
                add(unblockAll);
                add(listScrollPane);
                //add(dataList);
                b.addActionListener(this);
                b2.addActionListener(this);
                b3.addActionListener(this);
                blockAll.addActionListener(this);
                unblockAll.addActionListener(this);
                removeIP.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
                numClicks++;
                String s = e.getActionCommand(); 
                String ipAddress;
                if (s.equals("Block IP")) 
        		{ System.out.println("Block");
        			try
        			{
        				// Process process = Runtime.getRuntime().exec("notepad.exe");
        				// Process process = Runtime.getRuntime().exec("sudo iptables -A INPUT -s 98.139.180.149 -j DROP"); 
        				Process process = Runtime.getRuntime().exec("sudo iptables -A INPUT -s "+text.getText()+" -j DROP");
        				//System.out.println("Block");
        			}
        			catch (IOException exc)
        			{
        				exc.printStackTrace();
        			}	
        		} 	
        		else if(s.equals("Unblock IP")) 
        		{ 
        			try
        			{
        				// Process process = Runtime.getRuntime().exec("notepad.exe");
        				// Process process = Runtime.getRuntime().exec("sudo iptables -A INPUT -s 98.139.180.149 -j DROP"); 
        				//Process process = Runtime.getRuntime().exec("sudo iptables -D INPUT -s 98.139.180.149 -j DROP");
        				Process process = Runtime.getRuntime().exec("sudo iptables -D INPUT -s "+text.getText()+" -j DROP");
        			}
        			catch (IOException exc)
        			{
        				exc.printStackTrace();
        			}	
        		} 	
        		else if(s.equals("Add IP")) 
        		{ 
        			
        				String str1 = employeeName.getText();
        				System.out.println("add");
        				listModel.addElement(str1);
        				//dataList.
        				// Process process = Runtime.getRuntime().exec("notepad.exe");
        				// Process process = Runtime.getRuntime().exec("sudo iptables -A INPUT -s 98.139.180.149 -j DROP"); 
        				//Process process = Runtime.getRuntime().exec("sudo iptables -D INPUT -s 98.139.180.149 -j DROP");
        				//Process process = Runtime.getRuntime().exec("sudo iptables -D INPUT -s "+text.getText()+" -j DROP");
        			
        		} 	
        		else if(s.equals("Remove IP")) 
        		{
        			int index = list.getSelectedIndex();
        			listModel.remove(index);
        		}
        		else if(s.equals("Block All")) 
        		{
        			for(int i = 0; i < listModel.getSize(); i++) 
        			{
        			     System.out.println(listModel.getElementAt(i));
        			     ipAddress = (String) listModel.getElementAt(i);
        			     try
        			     {
        			    	 Process process = Runtime.getRuntime().exec("sudo iptables -A INPUT -s "+ipAddress+" -j DROP");
        			     }
        			     catch (IOException exc)
             			{
             				exc.printStackTrace();
             			}	 
        			 }
        		}
        		else if(s.equals("Unblock All")) 
            	{
            		for(int i = 0; i < listModel.getSize(); i++) 
            		{
            		     System.out.println(listModel.getElementAt(i));
            		     ipAddress = (String) listModel.getElementAt(i);
            		     try
            		     {
            		    	 Process process = Runtime.getRuntime().exec("sudo iptables -D INPUT -s "+ipAddress+" -j DROP");
            		     }
            		     catch (IOException exc)
                		{
            		    	 exc.printStackTrace();
                		}	     
            		}
        			
        		}
                //text.setText("Button Clicked " + numClicks + " times");
                System.out.println(text.getText());
        }

        public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
        }

        public void windowOpened(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}

}
