import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class dashboard extends JPanel {
    JLabel hallway;
    JTextArea office;
    JLabel messageboard;
    JLabel officeboard;

    private Semaphore officeSemaphore = new Semaphore(1);
    private Semaphore hallwaySemaphore = new Semaphore(3);

    public dashboard() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        String initialText = "";
        Color bg = new Color(237, 236, 253);
        Color fg = new Color(7, 16, 19);

        office = new JTextArea(10, 30);
        office.setText(initialText);
        office.setBackground(bg);
        office.setForeground(fg);

        JScrollPane scrollPane = new JScrollPane(office);

        officeboard = new JLabel("Office Notice");

        String initialHallway = "<html></html>";
        hallway = new JLabel(initialHallway) {
            public Dimension getPreferredSize() {
                return new Dimension(300, 200);
            }

            public Dimension getMinimumSize() {
                return new Dimension(300, 200);
            }

            public Dimension getMaximumSize() {
                return new Dimension(300, 200);
            }
        };
        hallway.setBackground(bg);

        String initialmessage = "<html><font color=#A31621> Simulation starts </font></html>";
        messageboard = new JLabel(initialmessage) {
            public Dimension getPreferredSize() {
                return new Dimension(200, 20);
            }

            public Dimension getMinimumSize() {
                return new Dimension(200, 20);
            }

            public Dimension getMaximumSize() {
                return new Dimension(200, 20);
            }
        };
        hallway.setVerticalAlignment(SwingConstants.CENTER);
        hallway.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("TA's office - One person at a time"),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        leftPanel.add(scrollPane);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(officeboard);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Hallway - Three chairs"),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        rightPanel.add(hallway);
        rightPanel.add(messageboard);

        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(leftPanel);
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(rightPanel);

SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    public void postMessage(String m) {
        messageboard.setText("<html><font color=#C14953>" + m + "</font></html>");
    }

    public void officeMessage(String m) {
        officeboard.setText("<html><font color=#071013>" + m + "</font></html>");
    }

    public void enterRoom(String name) {
        office.append("Student " + name + " entered the office\n");
    }

    public String getHallwayText() {
        return hallway.getText();
    }

    private String removeLine(String m, String current) {
        int pos = current.indexOf(m);
        if (pos != -1) {
            current = current.substring(0, pos) + current.substring(pos + m.length());
        }
        return current;
    }

    public void leaveRoom(String name) {
        String current = office.getText();
        String m = "Student " + name + " entered the office\n";
        office.setText(removeLine(m, current));
    }

    public void waitHallway(String name) {
        try {
            hallwaySemaphore.acquire();
            String current = hallway.getText();
            current = current.substring(0, current.length() - 7) + "<br>Student " + name + " is waiting" + "</html>";
            hallway.setText(current);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void leaveHallway(String name) {
        String current = hallway.getText();
        String m = "<br>Student " + name + " is waiting";
        hallway.setText(removeLine(m, current));
        hallwaySemaphore.release();
    }

    private void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Java Synchronization Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }
}