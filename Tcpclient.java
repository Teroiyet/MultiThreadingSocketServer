package multiThreading;

import java.net.*;
import java.io.*;
public class TCPClient {
  public static void main(String[] args) throws Exception {
  try{
    Socket socket=new Socket("192.168.173.1",8888);
    final DataInputStream inStream=new DataInputStream(socket.getInputStream());
    final DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String numbre="",b="",c="",serverMessage="";
    while(!numbre.equals("bye")){
      System.out.println("Enter number :");
      numbre=br.readLine();
      outStream.writeUTF(numbre);
      outStream.flush();
      
      b=br.readLine();
      outStream.writeUTF(b);
      outStream.flush();
      
      c=br.readLine();
      outStream.writeUTF(c);
      outStream.flush();
      
      serverMessage=inStream.readUTF();
      System.out.println(serverMessage);
    }
    outStream.close();
    outStream.close();
    socket.close();
  }catch(Exception e){
    System.out.println(e);
  }
  }
}
