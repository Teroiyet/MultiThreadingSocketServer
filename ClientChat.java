                        /******************************************************
                         ******************************************************
                          * Developed By :                                   *
                          *       Aymen Teroiyet           ||  IPSAS   ||    * 
                          *                                ||  G.inf1  ||    *    
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
        login=l;        // username
        
        JFrame f=new JFrame(login);        //
        f.setSize(400,400);                //
                                           //
        JPanel p1=new JPanel();            //
        p1.setLayout(new BorderLayout());  //
                                           //
        JPanel p2=new JPanel();            // 
        p2.setLayout(new BorderLayout());  //          
                                           //
        tx=new JTextField();               //   create a graphical interface for the ChatApp
        p1.add(tx, BorderLayout.CENTER);   //
                                           //
        JButton b1=new JButton("Send");    //
        p1.add(b1, BorderLayout.EAST);     //
                                           //
        ta=new JTextArea();                //
        p2.add(ta, BorderLayout.CENTER);   //
        p2.add(p1, BorderLayout.SOUTH);    //
                                           //
        f.setContentPane(p2);              //
           
 
           
        try{
                 Socket socketClient= new Socket("localhost",8888);//create an object of type Socket establish a connection to the server on the server's IP(in this case we use the localhost) and server's PORT (8888)
                  os= new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));// bufferedWriter for the write(Mic) of string's data
                 
                  is =new BufferedReader(new InputStreamReader(socketClient.getInputStream()));// bufferedReader for the read(SPEAKER) of string's data
                 
                  os.write(login);// send the username to add it into the users's vector 
                  os.write("\r\n");
                  os.flush();
 
			
		    		
        }catch(Exception e){e.printStackTrace();}
    
    
        b1.addActionListener(new ActionListener(){        // the button event
            public void actionPerformed(ActionEvent ev){
                String s1=login+" : ";
                String s2=tx.getText();
                tx.setText("");
                try{
                    os.write(s1);// send the username to append it to the chat interface
                    os.write("\r\n");
                    os.flush(); 
                    os.write(s2); // send the message
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
                while((serverMsg = is.readLine())/*read the msg of other client switching by the server*/ != null&&serverMsg.charAt(0)!=' '){
                    System.out.println("from server: " + serverMsg);
                    ta.append(serverMsg+"\n");// append the msg to the chat interface
                }
                
        }catch(Exception e){e.printStackTrace();}   
    }
}
