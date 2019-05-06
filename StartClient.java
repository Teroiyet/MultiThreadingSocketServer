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

import javax.swing.JOptionPane;

public class StartClient{
    
    public static void main(String [] args){
        	   try{
        	          
        	        String name = JOptionPane.showInputDialog(null,"Enter your name :", "Username",
        	                JOptionPane.PLAIN_MESSAGE);
        	        System.out.println("Hello "+name);
                        ClientChat c=new ClientChat(name);   
                        Thread t1=new Thread(c);
                        t1.start();
	   }catch(Exception e){e.printStackTrace();}
        
    }
}
