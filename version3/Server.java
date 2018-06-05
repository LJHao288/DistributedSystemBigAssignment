import java.io.*;
import java.util.*;
import java.net.*;

public class Server{

  public static void main(String args[]){
      try{
        ServerSocket ss = new ServerSocket(12345);
		
		while(true){
			Socket s1 = ss.accept();
			Socket s2 = ss.accept();
			Thread t1 = new Thread(new ServerThread1( s1,s2));
			Thread t2 = new Thread(new ServerThread2( s1,s2));
			t1.start();
            t2.start();

            t1.join();
            t2.join();
			
		}

		
      }catch(Exception e){
        System.out.println(e);
      }
  }
}

class ServerThread1 extends Thread{
    Socket s1;
	Socket s2;
    public ServerThread1(Socket s1, Socket s2){
        this.s1 = s1;
		this.s2 = s2;
    }

    public void run(){
	  
      try{
			Scanner scanner1 = new Scanner(s1.getInputStream());
			PrintWriter print2 = new PrintWriter(s2.getOutputStream());
			while (true)
			while (scanner1.hasNextLine()) {
				String text1 = scanner1.nextLine();
				print2.println(text1);
				print2.flush();
			}
			

          


        }catch(Exception e){
        System.out.println(e);
      }
    }
}
class ServerThread2 extends Thread{
    Socket s1;
	Socket s2;
    public ServerThread2( Socket s1, Socket s2){

        this.s1 = s1;
		this.s2 = s2;
    }

    public void run(){
	  
      try{

			Scanner scanner2 = new Scanner(s2.getInputStream());
			PrintWriter print1 = new PrintWriter(s1.getOutputStream());

			while (true)
			while (scanner2.hasNextLine()) {
				String text2 = scanner2.nextLine();
				print1.println(text2);
				print1.flush();
			}
			         


        }catch(Exception e){
        System.out.println(e);
      }
    }
}