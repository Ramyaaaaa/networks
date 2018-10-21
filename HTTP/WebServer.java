import java.io.*;
import java.util.*;
import java.net.*;
class WebServer
{
    public static void main(String argv[]) throws Exception
    {
        String requestMessageLine;
        String fileName;
        ServerSocket listenSocket = new ServerSocket(6789);
        Socket connectionSocket = listenSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        requestMessageLine = inFromClient.readLine();
        StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);
        System.out.println(tokenizedLine);
        System.out.println(requestMessageLine);
        if (tokenizedLine.nextToken().equals("GET"))
        {
            fileName = tokenizedLine.nextToken();
            if(fileName.startsWith("/") == true)
                fileName = fileName.substring(1);
            File file = new File(fileName);
            int numOfBytes = (int) file.length();
            FileInputStream inFile = new FileInputStream(fileName);
            byte[] fileInBytes = new byte[numOfBytes];
            inFile.read(fileInBytes);
            outToClient.writeBytes("HTTP/1.0 200 Success\r\n");
            if(fileName.endsWith(".html"))
                outToClient.writeBytes("Content-Type:html\r\n");
            if(fileName.endsWith(".jpeg"))
                outToClient.writeBytes("Content-Type:image/jpeg\r\n");
            if(fileName.endsWith(".gif"))
                outToClient.writeBytes("Content-Type:image/gif\r\n");
            outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
            outToClient.writeBytes("\r\n");
            outToClient.write(fileInBytes, 0, numOfBytes);
            connectionSocket.close();
        }
        else
                outToClient.writeBytes("HTTP/1.0 400 Bad Request Message");
    }
}




/* OUTPUT

Request URL: http://localhost:6789/sample.html
Request Method: GET
Status Code: 200 Success
Remote Address: [::1]:6789
Referrer Policy: no-referrer-when-downgrade
Content-Length: 142
Content-Type: html
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
Accept-Encoding: gzip, deflate, br
Accept-Language: en-US,en;q=0.9,hi-IN;q=0.8,hi;q=0.7,en-IN;q=0.6
Cache-Control: max-age=0
Connection: keep-alive
Host: localhost:6789
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36

