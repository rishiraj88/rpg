package gam.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameMapVisitor {
    private static final Map<String, StringBuffer> gameMapBlocksVisited = new ConcurrentHashMap<>();

    public static void setMapGroundsVisited(int nsIdx,int ewIdx,String playerRef) {
        gameMapBlocksVisited.put(playerRef,new StringBuffer(nsIdx +";"+ewIdx));
    }
}
