import java.util.concurrent.*;

public class Student implements Runnable {
    private final String name;
    private final dashboard board;
    private final Semaphore officeSemaphore;
    private final Semaphore hallwaySemaphore;

    public Student(String name, dashboard board, Semaphore officeSemaphore, Semaphore hallwaySemaphore) {
        this.name = name;
        this.board = board;
        this.officeSemaphore = officeSemaphore;
        this.hallwaySemaphore = hallwaySemaphore;
    }

    public void run() {
        while (true) {
            // Programming for a while
            try {
                System.out.println(name + " is programming.");
                TimeUnit.SECONDS.sleep(5); // Simulating programming time

                // Seeking help from TA
                System.out.println(name + " needs help.");
                board.postMessage(name + " needs help.");

                // If the waiting room has space, wait in the hallway
                if (hallwaySemaphore.tryAcquire()) {
                    board.waitHallway(name);
                    System.out.println(name + " is waiting in the hallway.");

                    // Try to enter the office if TA is available
                    if (officeSemaphore.tryAcquire()) {
                        board.leaveHallway(name);
                        board.enterRoom(name);
                        System.out.println(name + " enters the office.");

                        // Simulate time with TA
                        System.out.println(name + " is getting help.");
                        TimeUnit.SECONDS.sleep(3); // Simulating time with the TA

                        board.leaveRoom(name);
                        officeSemaphore.release();
                        System.out.println(name + " leaves the office.");
                    } else {
                        board.leaveHallway(name);
                        System.out.println(name + " couldn't enter the office, coming back later.");
                    }
                } else {
                    System.out.println(name + " couldn't wait in the hallway, coming back later.");
                }

                // Student goes back to programming
                TimeUnit.SECONDS.sleep(5); // Simulating time before seeking help again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
