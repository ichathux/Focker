/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.ArrayList;

/**
 *
 * @author Isuru Bandara
 * done by Isuru Bandara
 */
public class Cards {
    private static final int DECK_SIZE = 52;
    
    ArrayList<Integer> createDeck(){
        ArrayList<Integer> deck = new ArrayList<Integer>();

        for (int i = 1; i <= DECK_SIZE; ++i) {
            deck.add(i);
        }
        return deck;
        
        
        
    }
    String nameCards(int i){
        ArrayList<Integer> createdDeck = createDeck(); 
        ArrayList<Integer> namedDeck = createdDeck;
        String[] suit = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String text;
        
            
            String suitx = suit[createdDeck.get(i) / 13];
            String rankx = ranks[createdDeck.get(i) % 13];
            //System.out.println( rankx + " of " + suitx);
            text =  rankx + " of " + suitx;
            return text;      
    }
    
    ArrayList<Integer> shuffleInt(ArrayList<Integer> deck){
        

        ArrayList<Integer> shuffledDeck = new ArrayList<Integer>();

        while (deck.size() > 0) {
            int index = (int) (Math.random() * deck.size());
            shuffledDeck.add(deck.remove(index));
        }
        System.out.println(shuffledDeck.toString());
        return shuffledDeck; 
    }
    /*
    void shuffle(ArrayList<Integer> deck){
        

        ArrayList<Integer> shuffledDeck = new ArrayList<Integer>();

        while (deck.size() > 0) {
            int index = (int) (Math.random() * deck.size());
            shuffledDeck.add(deck.remove(index));
        }
        //System.out.println(shuffledDeck.toString());
        
        getCard(shuffledDeck);
        
    }*/
    ArrayList<Integer> getCardFirst(ArrayList<Integer> shuffledDeck,int a, int b){
        //for(int j = 0; j <= b;j++){
        ArrayList<Integer> hand = new ArrayList<Integer>(2);    
        for(int i = a; i < 2+a ; ++i){               
                hand.add(shuffledDeck.get(i));       
            }
        //System.out.println(hand.toString());
        return hand;
   
    }
    ArrayList<Integer> getCardSecond(ArrayList<Integer> shuffledDeck,int a, int b){
        ArrayList<Integer> table = new ArrayList<Integer>();
        for(int i = a+1; i < a+2 ; ++i){
                table.add(shuffledDeck.get(i));
         }
        //System.out.println(table.toString());
        return table;
        
    }
    ArrayList<Integer> getCardThird(ArrayList<Integer> shuffledDeck, ArrayList<Integer> table, int a){
        
        for(int i = a+1; i < a+2 ; ++i){
                table.add(shuffledDeck.get(i));
         }
        //System.out.println(table.toString());
        return table;
        
    }
    ArrayList<Integer> getCardForth(ArrayList<Integer> shuffledDeck, ArrayList<Integer> table, int a){ 
        for(int i = a+6 ;i < a+7 ; ++i){
                table.add(shuffledDeck.get(i));
         }
        //System.out.println(table.toString());
        return table;
        
        
    }
    int share(int members){
        int mem = members;
        System.out.println("each : "+mem);
        return mem;
    }
}
