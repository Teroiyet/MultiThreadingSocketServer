package server;

import javax.swing.JOptionPane;

public class StartClient{
    
    public static void main(String [] args){
        	   try{
        	        System.out.println("Hello ");  
        	        String name = JOptionPane.showInputDialog(null,"Enter your name :", "Username",
        	                JOptionPane.PLAIN_MESSAGE);
                        ClientChat c=new ClientChat(name);   
                        Thread t1=new Thread(c);
                        t1.start();
	   }catch(Exception e){e.printStackTrace();}
        
    }
}
