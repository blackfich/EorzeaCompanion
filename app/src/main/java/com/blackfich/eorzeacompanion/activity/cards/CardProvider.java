package com.blackfich.eorzeacompanion.activity.cards;

import com.blackfich.eorzeacompanion.util.bean.Mapable;
import com.blackfich.eorzeacompanion.util.bean.Timable;

import java.util.List;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public interface CardProvider {

    public String getName();

    public static class Quest implements CardProvider {
        private String name;

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Donjon implements CardProvider {
        private String name;

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Pack implements CardProvider {
        private String name;
        private List<Card> cards;

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Card> getCards() {
            return cards;
        }

        public void setCards(List<Card> cards) {
            this.cards = cards;
        }
    }

    public static class NPC implements CardProvider, Timable, Mapable {
        private String name;
        private String map;
        private int x;
        private int y;
        private int startTime;
        private int endTime;
        private List<Card> deck;
        private List<Card> rewards;
        private int fee;
        private int win;
        private int draw;
        private int lose;
        private String preReq;

        @Override
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

        @Override
        public String getMap() {
            return map;
        }

        @Override
        public void setMap(String map) {
            this.map = map;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getWin() {
            return win;
        }

        public void setWin(int win) {
            this.win = win;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }

        public int getLose() {
            return lose;
        }

        public void setLose(int lose) {
            this.lose = lose;
        }

        public List<Card> getDeck() {
            return deck;
        }

        public void setDeck(List<Card> deck) {
            this.deck = deck;
        }

        public List<Card> getRewards() {
            return rewards;
        }

        public void setRewards(List<Card> rewards) {
            this.rewards = rewards;
        }

        public String getPreReq() {
            return preReq;
        }

        public void setPreReq(String preReq) {
            this.preReq = preReq;
        }
    }

    public static class Shop implements CardProvider {
        private int price;

        @Override
        public String getName() {
            return "shop";
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

}
