import java.util.concurrent.*;

public class TA implements Runnable {
    private final dashboard board;
    private final Semaphore officeSemaphore;
    private final Semaphore hallwaySemaphore;

    public TA(dashboard board, Semaphore officeSemaphore, Semaphore hallwaySemaphore) {
        this.board = board;
        this.officeSemaphore = officeSemaphore;
        this.hallwaySemaphore = hallwaySemaphore;
    }

    public void run() {
        while (true) {
            try {
                board.officeMessage("TA is sleeping.");
                System.out.println("TA is sleeping.");
                TimeUnit.SECONDS.sleep(5); // Simulating TA's nap time

                // Check if there are students waiting in the hallway before waking up
                if (hallwayHasStudents()) {
                    // Try to acquire the officeSemaphore to work with students
                    if (officeSemaphore.tryAcquire()) {
                        board.officeMessage("TA is working with students.");
                        System.out.println("TA is working with students.");
                        TimeUnit.SECONDS.sleep(3); // Simulating time with students
                        board.officeMessage("TA is done with students.");
                        officeSemaphore.release();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean hallwayHasStudents() {
        String hallwayText = board.getHallwayText();
        return hallwayText.contains("is waiting");
    }
}
