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

import javax.swing.JOptionPane;

public class StartClient{
    
    public static void main(String [] args){
        	   try{
        	          
        	        String name = JOptionPane.showInputDialog(null,"Enter your name :", "Username",JOptionPane.PLAIN_MESSAGE);//option panel to enter the username 
        	        System.out.println("Hello "+name);
                        ClientChat c=new ClientChat(name);// create a new object of type Clientchat(new Client) !!    
                        Thread t1=new Thread(c);//create a new thread 
                        t1.start();//launch the thread
	   }catch(Exception e){e.printStackTrace();}
        
    }
}
