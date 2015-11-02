package com.blackfich.eorzeacompanion.activity.cards;

import com.blackfich.eorzeacompanion.R;
import com.blackfich.eorzeacompanion.util.XmlUtil;
import com.blackfich.eorzeacompanion.util.bean.Mapable;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.XmlFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public abstract class CardProvider {

    public abstract String getName();

    public abstract void fromXml(Element element);

    public static abstract class CardProviderId {
        private final String id;
        private final String className;

        protected CardProviderId(String id) {
            this.id = id;
            this.className = this.getClass().getSimpleName();
        }

        public abstract int getDrawable();

        public abstract String getString();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CardProviderId that = (CardProviderId) o;

            if (!id.equals(that.id)) return false;
            return className.equals(that.className);

        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + className.hashCode();
            return result;
        }
    }

    public static class QuestCardProviderId extends CardProviderId {
        private final String questId;

        public QuestCardProviderId(String questId) {
            super(questId);
            this.questId = questId;
        }

        @Override
        public int getDrawable() {
            return R.drawable.triple_triad_provider_quest;
        }

        @Override
        public String getString() {
            return "quest_" + questId;
        }
    }

    public static class DeckCardProviderId extends CardProviderId {
        private final String deckType;

        public DeckCardProviderId(String deckType) {
            super(deckType);
            this.deckType = deckType;
        }

        @Override
        public int getDrawable() {
            return R.drawable.triple_triad_provider_deck;
        }

        @Override
        public String getString() {
            return "deck_" + deckType;
        }
    }


    public static class ShopCardProviderId extends CardProviderId {
        private final int price;

        public ShopCardProviderId(int price) {
            super("" + price);
            this.price = price;
        }

        @Override
        public int getDrawable() {
            return R.drawable.triple_triad_provider_buy;
        }

        @Override
        public String getString() {
            return "" + price;
        }
    }


    public static class AchievementCardProviderId extends CardProviderId {
        private final String achievementId;

        public AchievementCardProviderId(String achievementId) {
            super(achievementId);
            this.achievementId = achievementId;
        }

        @Override
        public int getDrawable() {
            return R.drawable.triple_triad_provider_achievement;
        }

        @Override
        public String getString() {
            return "achievement_" + achievementId;
        }
    }

    public static class NpcCardProviderId extends CardProviderId {
        private final String npcName;

        public NpcCardProviderId(String npcName) {
            super(npcName);
            this.npcName = npcName;
        }

        @Override
        public int getDrawable() {
            return R.drawable.triple_triad_provider_duel;
        }

        @Override
        public String getString() {
            return "npc_" + npcName;
        }
    }


    public static class DungeonCardProviderId extends CardProviderId {

        private final String dungeonName;

        public DungeonCardProviderId(String dungeonName) {
            super(dungeonName);
            this.dungeonName = dungeonName;
        }

        @Override
        public int getDrawable() {
            return R.drawable.triple_triad_provider_dungeon;
        }

        @Override
        public String getString() {
            return "dungeon_" + dungeonName;
        }
    }

    public static class Quest extends CardProvider {
        private String name;

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void fromXml(Element element) {
            name = XmlUtil.getAttribueValue(element, "name");
        }

    }

    public static class Donjon extends CardProvider {
        private String name;

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void fromXml(Element element) {
            name = XmlUtil.getAttribueValue(element, "name");
        }
    }

    public static class Pack extends CardProvider {
        private String name;
        private List<String> cards;

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getCards() {
            return cards;
        }

        public void setCards(List<String> cards) {
            this.cards = cards;
        }

        public void fromXml(Element element) {
            name = XmlUtil.getAttribueValue(element, "name");
            cards = loadProviderCards(element, "cards");
        }
    }

    public static class NPC extends CardProvider implements Timable, Mapable {
        private String name;
        private String map;
        private int x;
        private int y;
        private int startTime;
        private int endTime;
        private List<String> deck;
        private List<String> rewards;
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

        public List<String> getDeck() {
            return deck;
        }

        public void setDeck(List<String> deck) {
            this.deck = deck;
        }

        public List<String> getRewards() {
            return rewards;
        }

        public void setRewards(List<String> rewards) {
            this.rewards = rewards;
        }

        public String getPreReq() {
            return preReq;
        }

        public void setPreReq(String preReq) {
            this.preReq = preReq;
        }

        public void fromXml(Element element) {
            XmlFactory.fromXml((Mapable) this, element);
            XmlFactory.fromXml((Timable) this, element);
            name = XmlUtil.getAttribueValue(element, "name");
            deck = loadProviderCards(element, "deck");
            rewards = loadProviderCards(element, "rewards");
            fee = XmlUtil.getAttribueValue(element, "fee", 0);
            win = XmlUtil.getAttribueValue(element, "win", 0);
            lose = XmlUtil.getAttribueValue(element, "lose", 0);
            draw = XmlUtil.getAttribueValue(element, "draw", 0);
            preReq = XmlUtil.getAttribueValue(element, "pre-req");
        }
    }

    public static class Shop extends CardProvider {
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
        public void fromXml(Element element) {
            price = XmlUtil.getAttribueValue(element, "price", 0);
        }
    }

    private static List<String> loadProviderCards(Element element, String tagName) {
        List<String> cards = new ArrayList<>();
        NodeList nl = element.getElementsByTagName(tagName);
        if (nl.getLength() > 0) {
            nl = nl.item(0).getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                Node xmlNode = nl.item(i);
                if (!(xmlNode instanceof Element)) {
                    continue;
                }
                Element elt = (Element) xmlNode;
                if ("card".equals(elt.getTagName())) {
                    cards.add(XmlUtil.getAttribueValue(elt, "name"));
                }
            }
        }
        return cards;
    }
}
