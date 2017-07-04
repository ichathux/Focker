/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author Isuru Bandara
 * done by Isuru Bandara
 */


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    static ServerSocket serverSocket;
    static Socket socket;
    static DataOutputStream out;
    static Users[] user = new Users[8];
    static DataInputStream in;
    static int x = 0;
    static Cards cards = new Cards();
    static ArrayList<Integer> deck = cards.createDeck();
    static ArrayList<Integer> shuffledDeck = cards.shuffleInt(deck);
    static ArrayList<Integer> table = cards.getCardSecond(shuffledDeck,((2*x)-1),(x+2));
    static ArrayList<Integer> tableTwo = cards.getCardThird(shuffledDeck,table,(x+2));
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException{
         
        System.out.println("Starting server..");
        serverSocket = new ServerSocket(7777);
        System.out.println("Server started..");
        while(true){
            socket = serverSocket.accept();
            System.out.println("Connection from : "+socket.getInetAddress());
            for(int i = 0; i < 10; i++){
                
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                if(user[i] == null){
                    user[i] = new Users(out,in ,user);
                    Thread thread = new Thread(user[i]);
                        
                    thread.start();
                    x++;
                                           
                    List<List<Integer>> first = firstTake(deck,shuffledDeck);
                    List<List<Integer>> second = secondTake(deck, shuffledDeck);
                    List<List<Integer>> third = thirdTake(table, shuffledDeck);
                    List<List<Integer>> forth = fourthTake(tableTwo, shuffledDeck);
                    
                    List<Integer> temp1 = first.get(i);
                    out.writeUTF(first.get(i).toString()+ cards.nameCards(temp1.get(0))+" "+cards.nameCards(temp1.get(1)));
                    List<Integer> temp2 = second.get(i);
                    out.writeUTF(second.get(i).toString()+" "+ cards.nameCards(temp2.get(0)));
                    List<Integer> temp3 = third.get(i);
                    out.writeUTF(third.get(i).toString()+" "+ cards.nameCards(temp3.get(0)));
                    List<Integer> temp4 = forth.get(i);
                    out.writeUTF(forth.get(i).toString()+" "+ cards.nameCards(temp4.get(0)));

                    break;
                    
                }
            }
            
            
            if(x == 4){
                
    
                
                //firstTake(deck, shuffledDeck);
                //secondTake(deck, shuffledDeck);
                //thirdTake(table, shuffledDeck);
                //fourthTake(tableTwo, shuffledDeck);
                
            }else if(x > 6){
                System.out.println("Game already started. Max players are 6.");
            }
        }
        
    }
    static List<List<Integer>> firstTake(ArrayList<Integer> deck,ArrayList<Integer> shuffledDeck){
        System.out.println("Game started");
                
                System.out.println("Shuffled");
                List<List<Integer>> lists = new ArrayList<List<Integer>>();
                for(int i =0 ; i<(2*x);i++){
                    int y = 0;
                    int player =0;
                    player = (i+2)/2;
                    //System.out.println("Player "+player);
                    
                    lists.add(cards.getCardFirst(shuffledDeck,i,(x+2)));
                    i = i + 1;
                    //System.out.println(" ");
                    
                }
                System.out.println("Initial set");
                System.out.println(lists.toString());
                return lists;
    }
    static List<List<Integer>> secondTake(ArrayList<Integer> deck,ArrayList<Integer> shuffledDeck){
        List<List<Integer>> lists2 = new ArrayList<List<Integer>>();
        for(int i =(2*x-1) ; i<(x+7);i++){
            lists2.add(cards.getCardSecond(shuffledDeck,i,(x+2)));
        }
        System.out.println("1st turn");
        System.out.println(lists2.toString());
        return lists2;
    }
    static List<List<Integer>> thirdTake(ArrayList<Integer> table,ArrayList<Integer> shuffledDeck){
        List<List<Integer>> lists3 = new ArrayList<List<Integer>>();
        for(int i =((2*x-1)+x) ; i<(x+((2*x-1)+x));i++){
            lists3.add(cards.getCardSecond(shuffledDeck,i,(x+2)));
        }
        System.out.println("2nd turn");
        System.out.println(lists3.toString());
        return lists3;
    }
    static List<List<Integer>> fourthTake(ArrayList<Integer> table,ArrayList<Integer> shuffledDeck){
        List<List<Integer>> lists4 = new ArrayList<List<Integer>>();
        for(int i =((2*x-1)+2*x) ; i<(x+((2*x-1)+2*x));i++){
            lists4.add(cards.getCardSecond(shuffledDeck,i,(x+2)));
        }
        System.out.println("3rd turn");
        System.out.println(lists4.toString());
        return lists4;
    }
    
    static ArrayList<Integer> handRest(ArrayList<Integer> table,ArrayList<Integer> shuffledDeck, int players){
        int a ;
        ArrayList<Integer> listsRest = new ArrayList<Integer> ();
            
            for( int i = 0; i<x ; i++){
                
            }
       
        return listsRest;
    }
    
    
    
}
class Users implements Runnable{
    
    DataOutputStream out;
    DataInputStream in;
    Users[] user = new Users[8];
    String name;
    
    public Users(DataOutputStream out, DataInputStream in, Users[] user){
    
        this.out = out;
        this.in = in;
        this.user = user;
    }
    public void run(){
        try {
            name = in.readUTF();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        while(true){
            try{
                
                String message = in.readUTF();
                //ArrayList<Integer> deck = cards.createDeck();
                //ArrayList<Integer> shuffledDeck = cards.shuffleInt(deck);
                for(int i = 0; i<8; i++){
                    int size =0;
                    if (user[i] != null){
                        size++;
                        user[i].out.writeUTF(name+" : "+message);
                    }
                    //if(size == 4){
                        
                        //List<List<Integer>> lists = new ArrayList<List<Integer>>();
                        //lists = firstTake(deck,shuffledDeck);
                        //user[i].in.readUTF((DataInput) lists.get(i));
                    //}
                }    
            }catch(IOException e){
                this.out = null;
                this.in = null;
                }
            }
        
        }

    private List<List<Integer>> firstTake(ArrayList<Integer> deck, ArrayList<Integer> shuffledDeck) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    }
