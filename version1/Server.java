import java.io.*;
import java.util.*;
import java.net.*;

public class Server{

  public static void main(String args[]){
	  Object lock = new Object();
      try{
        ServerSocket ss = new ServerSocket(12345);
		
		while(true){
			Socket s1 = ss.accept();
			Socket s2 = ss.accept();
			new Thread(new ServerThread(lock, s1,s2)).start();
		}

		
      }catch(Exception e){
        System.out.println(e);
      }
  }
}

class ServerThread extends Thread{
    Object lock;
    Socket s1;
	Socket s2;
    public ServerThread(Object lock, Socket s1, Socket s2){
        this.lock = lock;
        this.s1 = s1;
		this.s2 = s2;
    }

    public void run(){
	  while (true)
      try{
			Scanner scanner1 = new Scanner(s1.getInputStream());
			Scanner scanner2 = new Scanner(s2.getInputStream());
			PrintWriter print1 = new PrintWriter(s1.getOutputStream());
			PrintWriter print2 = new PrintWriter(s2.getOutputStream());
          synchronized(lock){
			String text1 = scanner1.nextLine();
			String text2 = scanner2.nextLine();
			print1.println(text2);
			print1.flush();
			print2.println(text1);
			print2.flush();

          }


        }catch(Exception e){
        System.out.println(e);
      }
    }
}