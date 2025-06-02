package gam.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameMapVisitor {
    private final Map<String, StringBuffer> gameMapBlocksVisited = new ConcurrentHashMap<>();

    public void setMapGroundsVisited(int nsIdx,int ewIdx,String playerRef) {
        gameMapBlocksVisited.put(playerRef,new StringBuffer(nsIdx +";"+ewIdx));
    }
}
