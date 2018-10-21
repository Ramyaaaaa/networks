import java.net.*;
import java.io.*;

public class httpserver
{
	public static void main(String args[])
	{
		try
		{
			ServerSocket ss = new ServerSocket(7919);
			Socket cl = ss.accept();
			System.out.println("Accepted client " + cl + "\nWaiting for response...");
			String s = null;
			DataInputStream dis = new DataInputStream(cl.getInputStream());
			BufferedOutputStream dos = new BufferedOutputStream(cl.getOutputStream());
			while ((s=dis.readUTF()) == null);
			
			URL url = new URL(s);
		
			HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
			System.out.println("Protocol: " + url.getProtocol());
			System.out.println("HostName: " + url.getHost());
					
			//urlConn.setDoInput(true);
			//urlConn.setUseCaches(false);
			InputStream is = urlConn.getInputStream();
			
			int i;
			
			//BufferedOutputStream b = new BufferedOutputStream(new FileOutputStream("//home//student//Desktop//test.html"));
			while ((i=is.read()) != -1) 
			{
				System.out.printf("%c",(char)i);
				dos.write((char)i);
			}
			//b.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}	
	}
}
