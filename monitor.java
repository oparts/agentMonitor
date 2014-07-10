

/*import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.net.SocketAddress; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
public class server {   
   public static void main(String[] args) { 
      try { 
         int port = 8888; 
         try (ServerSocket serverSock = new ServerSocket(port)) { 
            Logger logger = Logger.getLogger("Racar"); 
            while(true) { 
               Socket clientSock = serverSock.accept(); 
               SocketAddress clientAddress = clientSock.getRemoteSocketAddress(); 
               Thread thread = new Thread(new serverThread(clientSock, clientAddress, logger)); 
               thread.start(); 
               if(clientSock.isConnected()) 
               System.out.println("Connected! Client IP : " + clientAddress); 
            } 
         } 
         } catch (IOException ex) { 
         Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex); 
      } 
   } 
} 
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.ServerSocket; 
import java.net.Socket; 
import java.net.SocketAddress; 
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.logging.Level; 
import java.util.logging.Logger; 

public class monitor extends Thread {
	private DatagramSocket socket;
	
	public monitor() throws SocketException {
		super();

		socket = new DatagramSocket(9999);
	}

	public void run() {
		String data = null;
			
			try{				
				byte[] inbuf = new byte[256];
				DatagramPacket packet = new DatagramPacket(inbuf, inbuf.length);
				
				while (true)
				{
					socket.receive(packet);
		    			System.out.println("Sender : " + packet.getAddress()+ ", " + new String(packet.getData(), 0, packet.getLength()));	
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}


	}
	
	public static void main(String args[]) throws SocketException{
		monitor monitor = new monitor();
		monitor.start();
	}
}
