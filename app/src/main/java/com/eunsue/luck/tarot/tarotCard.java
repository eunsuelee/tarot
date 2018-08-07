package com.eunsue.luck.tarot;

import java.util.ArrayList;
import java.util.Random;

public class tarotCard extends cardBase{
    private ArrayList<Integer> randomCard = new ArrayList<>();

    public tarotCard(){
        super();
        setRandom();
    }

    public int getRandomCardIndex(int index){
        return randomCard.get(index);
    }

    public String getRandomCard(int index){
        int i = getRandomCardIndex(index);
        return this.getCard(i);
    }

    public void setRandom(){
        Random random = new Random();
        ArrayList<Integer> buf = new ArrayList<>();

        for(int i=0; i<22; i++){
            int index = random.nextInt(22);

            if(buf.contains(index)){
                i--;
            }
            else{
                buf.add(index);
            }
        }

        randomCard = buf;
    }
}
