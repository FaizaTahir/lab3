package serverClient;

import java.io.*;
import java.net.*;

class Server {

	private final static String fileOutput = "D:\\bla1.txt";
	
    public static void main(String args[]) {

        while (true) {
            ServerSocket welcomeSocket = null;
            Socket connectionSocket = null;
            byte[] aByte = new byte[1];
            int bytesRead;
            InputStream is = null;

            try {
                welcomeSocket = new ServerSocket(5555);
                connectionSocket = welcomeSocket.accept();
                is = connectionSocket.getInputStream();
            } catch (IOException ex) {
                // Do exception handling
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            if (is != null) {
            	 FileOutputStream fos = null;
                 BufferedOutputStream bos = null;
                 try {
                     fos = new FileOutputStream( fileOutput );
                     bos = new BufferedOutputStream(fos);
                     bytesRead = is.read(aByte, 0, aByte.length);

                     do {
                             baos.write(aByte);
                             bytesRead = is.read(aByte);
                     } while (bytesRead != -1);

                     bos.write(baos.toByteArray());
                     System.out.printf("File recieved");
                     bos.flush();
                     bos.close();
                     connectionSocket.close();
                 } catch (IOException ex) {
                     // Do exception handling
                 }
            
            }
        }
    }
}
