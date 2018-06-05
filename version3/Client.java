import java.util.*;
import java.io.*;
import java.net.*;

class Client
{
    public static void main(String[] args)
        throws Exception
    {

        try (
            Socket s = new Socket("localhost", 12345);
            Scanner sc = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream());
        ) {
            Thread t1 = new ClientToSrvThread(pw);
            Thread t2 = new SrvToClientThread(sc);

            t1.start();
            t2.start();

            t1.join();
            t2.join();
        }
    }
}

class ClientToSrvThread extends Thread
{
    PrintWriter pw;

    ClientToSrvThread(PrintWriter pw)
    {
        this.pw = pw;
    }

    public void run()
    {
		
        Scanner sc = new Scanner(System.in);
		while (true)
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
			//write to own txt
            pw.println(line);
            pw.flush();
			//out print all txt
        }
        // pw.flush();
    }
}

class SrvToClientThread extends Thread
{
    Scanner sc;

    SrvToClientThread(Scanner sc)
    {
        this.sc = sc;
    }

    public void run()
    {
        while (true)
        while (sc.hasNextLine()) {

            String sor = sc.nextLine();
			System.out.println(sor);
			//write to txt
            System.out.flush();
			//out print all txt
        }
        //System.out.println("dead!");
    }
}
