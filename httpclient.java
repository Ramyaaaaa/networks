import java.net.*;
import java.io.*;
import java.util.*;

public class httpclient
{
	public static void main(String args[])
	{
		try
		{
			Socket s = new Socket("localhost",7919);
			InputStream is = s.getInputStream();
			DataOutputStream os = new DataOutputStream(s.getOutputStream());
			System.out.println("Enter URL...");
			Scanner sc = new Scanner(System.in);
			String url = sc.nextLine();
			os.writeUTF(url);
			String r = null;
			int c;
			while ((c=is.read()) == -1);
			System.out.println("Writing to file...");
			BufferedOutputStream b = new BufferedOutputStream(new FileOutputStream("100.html"));
			b.write((char)c);
			while ((c=is.read()) != -1) b.write((char)c);
			System.out.println("Written!!");
			b.close();
			is.close();
			os.close();
			s.close();	
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}	
		
