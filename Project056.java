import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Project056 extends Application {

    private static Stage primarystage;
    private static BorderPane login;
    private static BorderPane table;
    private static BorderPane sign;
    @Override
    public void start(Stage primarystage) throws Exception {

    	this.primarystage=primarystage;

    	showlogin();

	 }

    public void heloo(){
    System.out.println("hello");
    }

        public static void showlogin() throws IOException{
       	FXMLLoader loader = new FXMLLoader();
       	loader.setLocation(Project056.class.getResource("FXMLDocument.fxml"));
        login=loader.load();
        Scene scene=new Scene(login);
        primarystage.setScene(scene);
        primarystage.setTitle("login");
        primarystage.show();


       }



       public static void showtable() throws IOException{
    	   FXMLLoader fxloader =new FXMLLoader();
    	    fxloader.setLocation(Project056.class.getResource("Tabledoc.fxml"));
    	    table=fxloader.load();
    	    Scene scene=new Scene(table);
            primarystage.setScene(scene);
            primarystage.setTitle("Table");
            primarystage.show();
       }



       public static void showsignup() throws IOException{

    	   FXMLLoader fxloader =new FXMLLoader();
   	       fxloader.setLocation(Project056.class.getResource("Signupdoc.fxml"));
   	       sign =fxloader.load();
   	       Scene scene=new Scene(sign);
           primarystage.setScene(scene);
           primarystage.setTitle("Sign up");
           primarystage.show();

       }



       public static void clientRequest(String username,String password,String title) throws UnknownHostException, IOException{
    	   String serverName = "hamidreza";
    	   int port = 12346;
    	   Socket client = new Socket("localhost", port);
           OutputStream outToServer = client.getOutputStream();
           DataOutputStream out = new DataOutputStream(outToServer);
           out.writeUTF(title);
           OutputStream outToServer1 = client.getOutputStream();
           DataOutputStream out1 = new DataOutputStream(outToServer1);
           out1.writeUTF(username+" "+password);
           //InputStream inFromServer = client.getInputStream();
           //DataInputStream in = new DataInputStream(inFromServer);
           //String response= in.readUTF();
           client.close();
           //return response;
       }



       public static String clientloginRequest(String username,String password,String title) throws UnknownHostException, IOException{
    	   String serverName = "hamidreza";
    	   int port = 12346;
    	   Socket client = new Socket("localhost", port);
           OutputStream outToServer = client.getOutputStream();
           DataOutputStream out = new DataOutputStream(outToServer);
           out.writeUTF(title);
           OutputStream outToServer1 = client.getOutputStream();
           DataOutputStream out1 = new DataOutputStream(outToServer1);
           out1.writeUTF(username+" "+password);
           InputStream inFromServer = client.getInputStream();
           DataInputStream in = new DataInputStream(inFromServer);
           String response= in.readUTF();
           client.close();
           return response;
       }


       public static void loadrequest() throws UnknownHostException, IOException{
    	   byte[] mybytearray = new byte[40000];
    	   String serverName = "hamidreza";
    	   int port = 12346;
    	   Socket client = new Socket("localhost", port);
           OutputStream outToServer = client.getOutputStream();
           DataOutputStream out = new DataOutputStream(outToServer);
           out.writeUTF("load");
          InputStream is = client.getInputStream();
          FileOutputStream fos = new FileOutputStream("/Users/hamidreza/Documents/JAVA WORKSPCE/Client/File info/serverfile.txt");
	      BufferedOutputStream bos = new BufferedOutputStream(fos);
	      int bytesRead = is.read(mybytearray, 0, mybytearray.length);
	      bos.write(mybytearray, 0, bytesRead);
	      bos.close();
	      client.close();





       }



       public static void filerequest(String filename) throws UnknownHostException, IOException{

    	   byte[] mybytearray = new byte[40000];
    	   String serverName = "hamidreza";
    	   int port = 12346;
    	   Socket client = new Socket("localhost", port);
           OutputStream outToServer = client.getOutputStream();
           DataOutputStream out = new DataOutputStream(outToServer);
           out.writeUTF("update");
           out.writeUTF(filename);
          InputStream is = client.getInputStream();
          FileOutputStream fos = new FileOutputStream("/Users/hamidreza/Documents/JAVA WORKSPCE/Client/Client Repository/"+filename);
	      BufferedOutputStream bos = new BufferedOutputStream(fos);
	      int bytesRead = is.read(mybytearray, 0, mybytearray.length);
	      bos.write(mybytearray, 0, bytesRead);
	      bos.close();
	      client.close();

       }


      public static void filecommit(String filename) throws UnknownHostException, IOException{
   	      String serverName = "hamidreza";
   	      int port = 12346;
   	      Socket client = new Socket("localhost", port);
          OutputStream outToServer = client.getOutputStream();
          DataOutputStream out = new DataOutputStream(outToServer);
          out.writeUTF("commit");
          out.writeUTF(filename);
          File myFile = new File("/Users/hamidreza/Documents/JAVA WORKSPCE/Client/Client Repository/"+filename);
          byte[] mybytearray2 = new byte[(int) myFile.length()];
          BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
          bis.read(mybytearray2, 0, mybytearray2.length);
          OutputStream os = client.getOutputStream();
          os.write(mybytearray2, 0, mybytearray2.length);
          os.flush();
          bis.close();
          client.close();
       }



      public static void filelock(String filename) throws UnknownHostException, IOException{
    	  String serverName = "hamidreza";
   	      int port = 12346;
   	      Socket client = new Socket("localhost", port);
          OutputStream outToServer = client.getOutputStream();
          DataOutputStream out = new DataOutputStream(outToServer);
          out.writeUTF("lock");
          out.writeUTF(filename);
          client.close();
      }



      public static void filesteal(String filename) throws UnknownHostException, IOException{
    	  String serverName = "hamidreza";
   	      int port = 12346;
   	      Socket client = new Socket("localhost", port);
          OutputStream outToServer = client.getOutputStream();
          DataOutputStream out = new DataOutputStream(outToServer);
          out.writeUTF("open");
          out.writeUTF(filename);
          client.close();
      }







    public static void main(String[] args) {
        launch(args);
    }




}
