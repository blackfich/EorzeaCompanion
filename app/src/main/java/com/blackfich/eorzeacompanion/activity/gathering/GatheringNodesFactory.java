package com.blackfich.eorzeacompanion.activity.gathering;

import android.content.res.AssetManager;
import android.util.Log;

import com.blackfich.eorzeacompanion.util.XmlUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marc Fichant on 07/10/2015.
 */
public class GatheringNodesFactory {

    public static List<GatheringNode> loadGatheringNodes(AssetManager assetManager) {
        List<GatheringNode> nodes = new ArrayList<GatheringNode>();
        Document doc = XmlUtil.getDomElement(assetManager, "data/nodes.xml");

        NodeList nl = doc.getChildNodes();
        nl = nl.item(0).getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            Node xmlNode = nl.item(i);
            if (!(xmlNode instanceof Element)) {
                continue;
            }
            Element element = (Element) xmlNode;
            GatheringNode node = null;
            if ("unspoiled-node".equals(element.getTagName())) {
                node = newUnspoiledNode(element);
            } else if ("ephemeral-node".equals(element.getTagName())) {
                node = newEphemeralNode(element);
            } else if ("folklore-node".equals(element.getTagName())) {
                node = newFolkloreNode(element);
            } else {
                Log.w("loadGatheringNodes", "Unknown gathering node type <" + element.getTagName() + ">");
            }
            if (node != null) {
                nodes.add(node);
                if (node.isTwicePerDay()) {
                    GatheringNode node2 = node.duplicate();
                    node2.setStartTime((node.getStartTime() + 1200) % 2400);
                    nodes.add(node2);
                }
            }
        }

        Collections.sort(nodes);

        return nodes;
    }

    private static UnspoiledNode newUnspoiledNode(Element element) {
        return fromXml(new UnspoiledNode(), element);
    }

    private static EphemeralNode newEphemeralNode(Element element) {
        return fromXml(new EphemeralNode(), element);
    }

    private static FolkloreNode newFolkloreNode(Element element) {
        return fromXml(new FolkloreNode(), element);
    }

    private static <T extends GatheringNode> T fromXml(T node, Element element) {
        node.fromXml(element);
        NodeList nl = element.getElementsByTagName("item");
        List<GatheringNodeItem> items = new ArrayList<GatheringNodeItem>();
        for (int i = 0; i < 8; i++)
            items.add(null);
        for (int i = 0; i < nl.getLength(); i++) {
            Node xmlNode = nl.item(i);
            if (!(xmlNode instanceof Element)) {
                continue;
            }
            Element xmlItem = (Element) xmlNode;
            GatheringNodeItem item = new GatheringNodeItem();
            item.fromXml(xmlItem);
            Log.d("debug", item.toString());
            items.set(item.getSlot() - 1, item);
        }
        node.setItems(items);
        return node;
    }


}
