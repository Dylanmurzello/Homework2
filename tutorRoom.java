import java.util.concurrent.Semaphore;

public class tutorRoom {
    public static void main(String args[]) {
        dashboard board = new dashboard();
        Semaphore officeSemaphore = new Semaphore(1); // Semaphore for controlling office access
        Semaphore hallwaySemaphore = new Semaphore(3); // Semaphore for controlling hallway chairs

        Thread teachingAssistant = new Thread(new TA(board, officeSemaphore, hallwaySemaphore), "TA");

        String[] names = {"Mary", "Emma", "Jennifer", "Mike", "Alan", "Bruce", "Tom"};
        Thread[] collegeStudents = new Thread[7];

        for (int i = 0; i < 7; i++) {
            collegeStudents[i] = new Thread(new Student(names[i], board, officeSemaphore, hallwaySemaphore), "Student " + i);
        }

        teachingAssistant.start();
        for (Thread student : collegeStudents) {
            student.start();
        }
    }
}
