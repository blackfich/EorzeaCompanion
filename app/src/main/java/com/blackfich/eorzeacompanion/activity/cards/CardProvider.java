package com.blackfich.eorzeacompanion.activity.cards;

import com.blackfich.eorzeacompanion.util.bean.Timable;

import java.util.List;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public interface CardProvider {

    public static class Quest implements  CardProvider {
        private String name;
    }

    public static class Donjon implements  CardProvider {
        private String name;
    }

    public static class Pack implements  CardProvider {
       private String name;
    }

    public static class NPC implements  CardProvider, Timable {
        private String name;
        private int startTime;
        private int endTime;
        private List<Card> deck;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int getStartTime() {
            return startTime;
        }

        @Override
        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        @Override
        public int getEndTime() {
            return endTime;
        }

        @Override
        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        @Override
        public boolean isTwicePerDay() {
            return false;
        }

        @Override
        public void setTwicePerDay(boolean twicePerDay) {

        }

        public List<Card> getDeck() {
            return deck;
        }

        public void setDeck(List<Card> deck) {
            this.deck = deck;
        }
    }

    public static class Shop implements  CardProvider {
        private int price;
    }

}
