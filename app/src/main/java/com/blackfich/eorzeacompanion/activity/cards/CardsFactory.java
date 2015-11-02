package com.blackfich.eorzeacompanion.activity.cards;

import android.content.res.AssetManager;

import com.blackfich.eorzeacompanion.activity.gathering.GatheringNode;
import com.blackfich.eorzeacompanion.util.XmlUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc Fichant on 29/10/2015.
 */
public class CardsFactory {

    public static List<Card> loadCards(AssetManager assetManager) {
        List<Card> cards = new ArrayList<Card>();
        Document doc = XmlUtil.getDomElement(assetManager, "data/cards.xml");

        NodeList nl = doc.getChildNodes();
        nl = nl.item(0).getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            Node xmlNode = nl.item(i);
            if (!(xmlNode instanceof Element)) {
                continue;
            }
            Element element = (Element) xmlNode;
            Card card = null;

            if ("card".equals(element.getTagName())) {
                card = newCard(element);
            }

            if (card != null) {
                cards.add(card);
            }
        }
        return cards;
    }

    private static Card newCard(Element element) {
        Card card = new Card();
        card.fromXml(element);

        return card;
    }
}
