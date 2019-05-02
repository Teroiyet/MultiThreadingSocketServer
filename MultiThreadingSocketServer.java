package server;
import java.net.*;
import java.util.Vector;

import java.io.*;
public class MultiThreadedSocketServer {
  public static void main(String[] args) throws Exception {
    try{
    	Vector<ServerClientThread> clients ;
    	clients=new Vector();
      ServerSocket server=new ServerSocket(8888);
      int counter=0;
      //InetAddress  ip ;
      //ip = InetAddress.getLocalHost();
      System.out.println("Server Started ....");
      while(true){
    	   
        counter++;
        Socket serverClient=server.accept();  //server accept the client connection request
        System.out.println(" >> " + "Client No:" + counter + " started!");
        ServerClientThread sct = new ServerClientThread(serverClient,counter); //send  the request to a separate thread
        
         clients.addElement(sct);
         clients.elementAt(counter).start();
         
      }
      
    }catch(Exception e){
      System.out.println(e);
    }
  }
}
