/*
 * Client connects to server port 1234.
 *Using Sockets Client communicates with the server
 */
package clientsocket;
import java.io.*;
import java.net.*;
import java.util.*;
/**
  * @author Daniel Mutwiri
 */
public class ClientSocket {
    private static InetAddress host;
    private static final int PORT = 1234;
    /**
            * @param args the command line arguments
         */
    public static void main(String[] args) {
        try{
            host = InetAddress.getLocalHost();
        }
        catch(UnknownHostException uhEx){
            System.out.println("Host ID Not Found!");
            System.out.println("Exiting....");
            System.exit(1);
        }
        accessServer();
    }
    private static void accessServer(){
        Socket link = null;
        try{
            link = new Socket(host, PORT);
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);
            //Set up Stream for keybaord entry...
            Scanner UserEntry = new Scanner(System.in);
            String message, response;
            do{
                System.out.print("Enter message: ");
                message = UserEntry.nextLine();
                output.println(message);
                response = input.nextLine();
                System.out.println("\nServer> " + response);
                }while(!message.equals("***CLOSE***"));
        }
        catch(IOException ioEx){
            ioEx.printStackTrace();
        }
        finally{
            try{
                System.out.println("\n**CLOSING CONNECTION...");
                link.close();
            }catch(IOException ioEx){
                System.out.println("Unable to Disconnect");
                System.exit(1);
            }
        }
    }
}
