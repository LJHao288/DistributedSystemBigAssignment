import java.util.*;
import java.io.*;
import java.net.*;

class Client
{
    public static void main(String[] args)
        throws Exception
    {
		BlockChain blockChain = new BlockChain();
        try (
            Socket s = new Socket("localhost", 12345);
            Scanner sc = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream());
			
        ) {
            Thread t1 = new ClientToSrvThread(pw,blockChain);
            Thread t2 = new SrvToClientThread(sc,blockChain);

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
	BlockChain blockChain;
    ClientToSrvThread(PrintWriter pw,BlockChain blockChain)
    {
        this.pw = pw;
		this.blockChain = blockChain;
    }

    public void run()
    {
		
        Scanner sc = new Scanner(System.in);
		while (true)
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
			
			//if "0" show the array list
			if(line.equals("0")){
				System.out.println(blockChain.toString());
			}else{
				
				pw.println(line);
				pw.flush();
				//add string to local block chain
				String[] ts = {line};
				blockChain.addBlock(ts);
			}
			
			
        }

    }
}

class SrvToClientThread extends Thread
{
    Scanner sc;
	BlockChain blockChain;
    SrvToClientThread(Scanner sc,BlockChain blockChain)
    {
        this.sc = sc;
		this.blockChain = blockChain;
    }

    public void run()
    {
        while (true)
        while (sc.hasNextLine()) {
            String sor = sc.nextLine();
			System.out.println(sor);
            System.out.flush();
			//add save string to local block chain
			String[] ts = {sor};
			blockChain.addBlock(ts);
        }
    }
}
