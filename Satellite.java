package server;

import java.io.*;
import java.net.*;
import java.util.*;
        
class Satellite implements Runnable{
 Socket connectionSocket;     
 
public static Vector clients=new Vector();// create a vector as a static variable to store all clients.

public Satellite(Socket connectionSocket)throws Exception {
	        
	         
             this.connectionSocket=connectionSocket;
    
}     
    public void run(){
            try{
            	
                 BufferedReader is = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                           
                 BufferedWriter os = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
                            
                            clients.add(os); //for evrey client got connected we need to store its BufferedWriter object inside the Vector list
                            
                        while(true){
                            String data1 = is.readLine().trim();//the trim() method to eliminate the leading and trailling spaces 
                            System.out.println("Received : "+data1);      
                            
                            for (int i=0;i<clients.size();i++){
                               try{
                                    BufferedWriter bw= (BufferedWriter)clients.get(i);//iterate through all writer objects 
                                    bw.write(data1);                                  //and sends the message back to the 
                                    bw.write("\r\n");                                 //clients 
                                    bw.flush();
                                }catch(Exception e){e.printStackTrace();}
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
