package com.eunsue.luck.tarot;

import java.util.ArrayList;
import java.util.Random;

public class tarotCard {
    private ArrayList<Integer> card = new ArrayList<>();
    private ArrayList<Integer> randomCard = new ArrayList<>();

    public int getCard(int index){
        return this.card.get(index);
    }

    public int getRandomCard(int index){
        setRandom();
        int i = getCard(randomCard.get(index));
        return this.getCard(i);
    }

    public void setRandom(){
        Random random = new Random();
        ArrayList<Integer> buf = new ArrayList<>();

        for(int i=0; i<50; i++){
            int index = random.nextInt(50)+1;

            if(buf.contains(index)){
               i--;
            }
            else{
                buf.add(index);
            }
        }

        randomCard = buf;
    }

    //card에 default 값 생성
    //get random card 좀 수정해야할듯
}
