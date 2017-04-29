package net.dean.jraw.models.factories;

import com.fasterxml.jackson.databind.JsonNode;
import net.dean.jraw.models.*;

public class ContributionFactory {

    public static <T extends Contribution> Contribution newContribution(T contribution, JsonNode replyDataNode){

        if(contribution instanceof PrivateMessage){
            return new PrivateMessage(replyDataNode);
        } else if(contribution instanceof CommentMessage){
            return new CommentMessage(replyDataNode);
        } else if(contribution instanceof Comment){
            return new Comment(replyDataNode);
        } else {
            return null;
        }

    }

}
