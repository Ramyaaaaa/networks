import java.io.*;
import java.net.*;
import java.util.*;
class Client
{
    public static void main(String args[])
    {
        try
        {
            DatagramSocket client=new DatagramSocket();
            InetAddress addr=InetAddress.getByName("127.0.0.1");
            byte[] sendbyte=new byte[1024];
            byte[] receivebyte=new byte[1024];
            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
            while(true)
            {
                System.out.print("Enter your choice : 1. DNS\t 2. Reverse DNS\t 3. Exit\n- -\b\b");
                int n = Integer.parseInt(System.console().readLine());
                if(n==1)
                {
                    sendbyte = Integer.toString(n).getBytes();
                    DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr,1309);
                    client.send(sender);
                    System.out.println("Enter the DOMAIN NAME :");
                    String str=in.readLine();
                    sendbyte=str.getBytes();
                    sender=new DatagramPacket(sendbyte,sendbyte.length,addr,1309);
                    client.send(sender);
                    DatagramPacket receiver=new DatagramPacket(receivebyte,receivebyte.length);
                    client.receive(receiver);
                    String s=new String(receiver.getData());
                    System.out.println("IP address : "+s.trim());
                }
                if(n==2)
                {
                    sendbyte = Integer.toString(n).getBytes();
                    DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr,1309);
                    client.send(sender); 
                    System.out.println("Enter the IP adress:");
                    String str=in.readLine();
                    sendbyte=str.getBytes();
                    sender=new DatagramPacket(sendbyte,sendbyte.length,addr,1309);
                    client.send(sender);
                    DatagramPacket receiver=new DatagramPacket(receivebyte,receivebyte.length);
                    client.receive(receiver);
                    String s=new String(receiver.getData());
                    System.out.println("DOMAIN NAME : "+s.trim());
                }
                if(n == 3)
                    break;
            }
            client.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}





/*


output

client

Enter your choice : 1. DNS	 2. Reverse DNS	 3. Exit
-1-
Enter the DOMAIN NAME :
www.google.com
IP address : 172.217.166.100
Enter your choice : 1. DNS	 2. Reverse DNS	 3. Exit
-2-
Enter the IP adress:
172.217.166.100
DOMAIN NAME : maa05s09-in-f4.1e100.net
Enter your choice : 1. DNS	 2. Reverse DNS	 3. Exit
-3-


server

1
www.google.com
2
172.217.166.100

*/
