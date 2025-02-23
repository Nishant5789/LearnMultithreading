package micellonous;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numPlayers = 3;
        CyclicBarrier barrier = new CyclicBarrier(numPlayers, () -> {
            System.out.println("All players are ready. Game starts now!");
        });

        for (int i = 1; i <= numPlayers; i++) {
            new Thread(new Player(barrier, "Player-" + i)).start();
        }
    }
}

class Player implements Runnable {
    private CyclicBarrier barrier;
    private String playerName;

    public Player(CyclicBarrier barrier, String playerName) {
        this.barrier = barrier;
        this.playerName = playerName;
    }

    public void run() {
        try {
            System.out.println(playerName + " is ready...");
            barrier.await(); // Wait for all players
            System.out.println(playerName + " starts playing.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
