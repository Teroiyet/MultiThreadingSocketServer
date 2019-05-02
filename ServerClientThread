package server;
import java.net.*;
import java.io.*;
class ServerClientThread extends Thread {
 Socket serverClient;
 int clientNo;
 int sqrt;
 ServerClientThread(Socket inSocket,int counter){
   serverClient = inSocket;
   clientNo=counter;
 }
  public static String baseConversion(String number, int sBase, int dBase) 
  {  
    return Integer.toString( Integer.parseInt(number, sBase),dBase); 
  } 
 public void run(){
   try{
     final DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
     final DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());

     String number="", serverMessage="",b="",c="";
     while(!number.equals("bye")){
       number=inStream.readUTF();
       b=inStream.readUTF();
       c=inStream.readUTF();
     
       int sBase = Integer.parseInt(b);
       int dBase = Integer.parseInt(c);
       
       
       System.out.println("From Client-" +clientNo+ ": Number is : "+number+" source base is : "+sBase+" distination base is : "+dBase);
       
       serverMessage="From Server to Client-" + clientNo + " : "+number+ " from base : "+sBase+" to base : "+dBase+" = "+baseConversion(number,sBase,dBase);
       outStream.writeUTF(serverMessage);
       outStream.flush();
     }
     inStream.close();
     outStream.close();
     serverClient.close();
   }catch(Exception ex){
     System.out.println(ex);
   }finally{
     System.out.println("Client -" + clientNo + " exit!! ");
   }
 }
}
