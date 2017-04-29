package net.dean.jraw.models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import net.dean.jraw.models.meta.Model;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** This class represents a message sent directly from one user to another */
@Model(kind = Model.Kind.MESSAGE)
public final class PrivateMessage extends Message {
    /** Instantiates a new PrivateMessage */
    public PrivateMessage(JsonNode dataNode) {
        super(dataNode);
    }

    public boolean isThread() {
        return getDataNode().get("replies").elements().hasNext()
                && getDataNode().get("replies").get("data").elements().hasNext();
    }

    public boolean hasReplies() {
        return isThread()
                && getDataNode().get("replies").get("data").get("children").elements().hasNext();
    }

    public List<PrivateMessage> getReplies() {
        List<PrivateMessage> replies = new ArrayList<>();

        if (isThread() && hasReplies()) {
            JsonNode repliesNode = this.getDataNode().get("replies");
            Iterator<JsonNode> iterator = repliesNode.get("data")
                    .get("children").elements();

            while (iterator.hasNext()) {
                JsonNode messageNode = iterator.next();
                PrivateMessage privateMessage = new PrivateMessage(messageNode.get("data"));
                replies.add(privateMessage);
            }
        }

        return replies;
    }

    public boolean hasUnseenChildren(){

        for (PrivateMessage pm : getReplies()) {
            if(!pm.isRead()) {
                return true;
            }
        }

        return false;
    }

    public void markThreadRead(boolean read){
        if(read) {
            ((ObjectNode) getDataNode()).put("new", "false");
        } else {
            ((ObjectNode) getDataNode()).put("new", "true");
        }
    }

    public void markChildrenAsRead(boolean read){
        for (PrivateMessage pm : getReplies()){
            if(read) {
                ((ObjectNode) pm.getDataNode()).put("new", "false");
            } else {
                ((ObjectNode) pm.getDataNode()).put("new", "true");
            }

        }
    }

}
