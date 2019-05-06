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

import java.io.*;
import java.net.*;
import java.util.*;
        
class Satellite implements Runnable{
 Socket connectionSocket;     
 
public static Vector clients=new Vector();// create a vector as a static variable to store all clients.
public static Vector users=new Vector();// create a vector as a static variable to store all clients's usernames.

public Satellite(Socket connectionSocket)throws Exception {
	        
	         
             this.connectionSocket=connectionSocket;
    
}     
public  String u(String m) {
	String user = "";
	String c ="";
	int i=0;
	while(m.charAt(i)!=' '&&i<m.length()) {
		c=String.valueOf(m.charAt(i));
		user+=c;
		i++;
	}
	return user;
}
public int index(String u ,Vector vector){
	int i=-1;
	do
	{
		i++;
		if(vector.elementAt(i).equals(u)) {
			return i;
		   }
		 
		
	}while(i<vector.size());
	return -1;
	}
public void broadcast(String data1 ,Vector clients) {

    for (int i=0;i<clients.size();i++){
       try{
            BufferedWriter bw= (BufferedWriter)clients.get(i);//iterate through all writer objects 
            bw.write(data1);                                  //and sends the message back to the 
            bw.write("\r\n");                                 //clients 
            bw.flush();
        }
       
       
       catch(Exception e){e.printStackTrace();}
    }
}
public void unicast(String data1 ,Vector clients,int index,int index2) {

    
       try{
            BufferedWriter bw= (BufferedWriter)clients.get(index);
            bw.write(data1);                                  //send the message back to the target client
            bw.write("\r\n");                                 
            bw.flush(); 
            if(index!=index2) {
            BufferedWriter bw1= (BufferedWriter)clients.get(index2);
            bw1.write(data1);                                  //sends the message back to the sender
            bw1.write("\r\n");                                 
            bw1.flush();
            }
        }
       
       
       catch(Exception e){e.printStackTrace();}
    }
public void multicast(String data1 ,Vector clients,int index) {

    for (int i=0;i<clients.size();i++){
       try{
    	   if(i!=index) {
            BufferedWriter bw= (BufferedWriter)clients.get(i);//iterate through all writer objects 
            bw.write(data1);                                  //and sends the message back to the 
            bw.write("\r\n");                                 //all clients except the indexed client 
            bw.flush();
    	   }
        }
       
       
       catch(Exception e){e.printStackTrace();}
    }
}

    public void run(){
            try{
            	
                 BufferedReader is = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                           
                 BufferedWriter os = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
                            
                            clients.add(os); //for every client got connected we need to store its BufferedWriter object inside a Vector list
                            String user = "@"+is.readLine().trim();
                        	users.add(user); //for every client got connected we need to store its username  inside a Vector list     
                        while(true){
                        	
                        	String login="@"+is.readLine().trim(); //the sender !! //the trim() method to eliminate the leading and trailling spaces 
                        	String login1 = login ;
                            String data1 = is.readLine().trim();// the received message !!
                            String data2 = login.replace(String.valueOf(login.charAt(0)),"")+data1;
                            System.out.println("Received : "+data1);
                            
                            if(data1.charAt(0)=='@') {
                           
                            	if(index(u(data1),users)==-1) {
                            		
                            		    broadcast(data2,clients);
                            		} 
                            	else {
                            		unicast(data2,clients,index(u(data1),users),index(u(login),users));
                            		}
                            	}
                            else if (data1.charAt(0)=='!'&&data1.charAt(1)=='@') {
                            	data1=data1.replace(String.valueOf(data1.charAt(0)),"");
                            	
                            	if(index(u(data1),users)==-1) {
                            		
                            		    broadcast(data2,clients);
                            		} 
                            	else {
                            		multicast(data2,clients,index(u(data1),users));
                            		}
                            }
                            else {	
                            	
                            	 broadcast(data2,clients);
                            }
                            
                        }    
                        
                    }catch(Exception e){e.printStackTrace();}
        }
        public static void main(String argv[]) throws Exception{
            System.out.println("Threaded Chat Server is Running  " );
            ServerSocket mysocket = new ServerSocket(8888);
                while(true){
                Socket sock = mysocket.accept();
                Satellite server=new Satellite(sock);
                Thread serverThread=new Thread(server);
                serverThread.start();
                }
            }
}
