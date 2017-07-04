package sample;
/**
 *
 * @author Isuru Bandara
 * done by Isuru Bandara
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import static cstest.Server.firstTake;
import java.io.*;
import java.net.*;
import java.util.*;
public class Main extends Application {

    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;
    static Cards cards = new Cards();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TableTheme.fxml"));
        primaryStage.setTitle("GSR Fokker");
        primaryStage.setScene(new Scene(root, 700, 450));
        primaryStage.show();

    }


    public static void main(String[] args) throws IOException {
        launch(args);
        System.out.println("Connecting...");
        socket = new Socket("localhost", 7777);
        System.out.println("Connection succeful.");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        Input input = new Input(in);
        Thread thread = new Thread(input);
        thread.start();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enteryour name and press enter. ");
        String name = sc.nextLine();
        out.writeUTF(name);
        
        //System.out.println("press 1 to shuffle");
        while(true){
            
            String sendMsg = sc.nextLine();
            out.writeUTF(sendMsg);
            
        }
    }
}
class Input implements Runnable{
    
    DataInputStream in;
    
    public Input(DataInputStream in){
        this.in = in;
    }
    
    @Override
    public void run() {
        while(true){
            //ArrayList<Integer> deck = cards.createDeck();
            //ArrayList<Integer> shuffledDeck = cards.shuffleInt(deck);
            //firstTake(deck, shuffledDeck);
            
            String message;
            try {
                
                message = in.readUTF();
                System.out.println(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
    }
    
}
