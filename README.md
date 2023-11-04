# Homework2
# University Computer Science Department Simulation

This project simulates the interaction between undergraduate students seeking help from a Teaching Assistant (TA) during regular office hours for their programming assignments. The scenario is set in a computer science department where the TA's office accommodates only one student at a time. There are three chairs placed in the hallway outside the office for waiting students.

## Project Overview

The code creates multiple threads representing the TA and the students. Each student thread alternates between programming and seeking help. The students spend a period programming and then attempt to access the TA's office for assistance.

### Tools Used

The project utilizes Semaphores to manage access to shared resources like the TA's office and hallway space. The code implements a simulated environment where students wait in the hallway if the TA is occupied or take a nap if no students are present. If a student finds the TA sleeping, they wake the TA to request help.

## How to Use

The code contains various Java classes - `TA`, `Student`, and more - simulating the behaviors of the TA and the students. To observe the scenario, run the `tutorRoom.java` file after compiling the entire project.

## Contribute

Feel free to contribute to this project by enhancing the simulation, adding new features, or refining the existing code to improve its accuracy or performance.

## License

This project is under the MIT License - see the [LICENSE](LICENSE) file for details.
