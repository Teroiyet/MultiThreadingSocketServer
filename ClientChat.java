                        /******************************************************
                         ******************************************************
                          * Developed By :                                   *
                          *       Aymen Teroiyet           ||  IPSAS   ||    * 
                          *                             ||  G.inf1  ||       *    
                          *            Omar Masmoudi                         * 
                          *                                                  *
                          *                 Abdallah Trabellsi               *
                          *                                        2018-2019 *
                         ******************************************************
                         ******************************************************/


package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class ClientChat implements Runnable{
    public JTextField tx;
    public JTextArea ta;
    public String login="";
    BufferedWriter os;
    BufferedReader is;
   
    public ClientChat(String l){
        login=l;        
        
        JFrame f=new JFrame("G-INF1 : IPSAS");
        f.setSize(400,400);        
        
        JPanel p1=new JPanel();
        p1.setLayout(new BorderLayout());
            
        JPanel p2=new JPanel();
        p2.setLayout(new BorderLayout());        
        
        tx=new JTextField();
        p1.add(tx, BorderLayout.CENTER);
        
        JButton b1=new JButton("Send");
        p1.add(b1, BorderLayout.EAST); 
        
        ta=new JTextArea();
        p2.add(ta, BorderLayout.CENTER);
        p2.add(p1, BorderLayout.SOUTH);
        
        f.setContentPane(p2);
           
 
           
        try{
                 Socket socketClient= new Socket("localhost",8888);
                  os= new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
                 
                  is =new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                 
                  os.write(login);
                  os.write("\r\n");
                  os.flush();
 
			
		    		
        }catch(Exception e){e.printStackTrace();}
    
    
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                String s1=login+" : ";
                String s2=tx.getText();
                tx.setText("");
                try{
                    os.write(s1);
                    os.write("\r\n");
                    os.flush(); 
                    os.write(s2);
                    os.write("\r\n");
                    os.flush();
                    }
                
                catch(Exception e){e.printStackTrace();}
            }
          }
        );
        
        f.setVisible(true);    
        
 
    }
    public void run(){
             try{
                String serverMsg=""; 
                while((serverMsg = is.readLine()) != null&&serverMsg.charAt(0)!=' '){
                    System.out.println("from server: " + serverMsg);
                    ta.append(serverMsg+"\n");
                }
                
        }catch(Exception e){e.printStackTrace();}   
    }
}
