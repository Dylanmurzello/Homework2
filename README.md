# University Computer Science Department Simulation

This Java project simulates the workflow within a university computer science department, specifically the interaction between undergraduate students and a Teaching Assistant (TA) during regular office hours. The system emulates the TA's office environment where students seek help for their programming assignments.

## Overview

The project demonstrates the typical situation in the TA's office, which is confined in space to accommodate only one student. Additionally, there are three chairs available in the hallway for students waiting to see the TA. The system portrays a scenario following the university's office hours, detailing how the TA and students interact in different circumstances.

## Functionality

- **TA Office Environment:** 
  - Only one desk and chair are available in the TA's office.
  - The hallway has three chairs for students waiting to see the TA.

- **Student Interaction:**
  - To start, multiple students (n students) are created, with each student running as a separate thread.
  - Students alternate between programming and seeking help from the TA.
  - If the TA is occupied, students wait in the hallway.
  - If the TA is sleeping and a student arrives, the student must wake the TA for assistance.
  - If no chairs are available in the hallway, the student returns later.

- **TA's Activities:**
  - The TA sleeps when no students are present.
  - When helping a student, the TA checks if there are other students waiting in the hallway and assists them in order.

## Synchronization Approach

This project utilizes Java synchronization tools:
- The synchronization methods used can be implemented with monitors using synchronized/wait()/notify() or semaphores and reentrant locks.
- The synchronization is maintained to manage interactions between the TA and multiple students running as separate threads.

## How to Use

- The project consists of various Java classes that demonstrate the behaviors of the TA and students.
- Compile the project and run the `tutorRoom.java` file to observe the interaction and functionalities.

## Contributions

Contributions to the project, including feature enhancements, code refinement, and performance improvements, are welcome. 

## License

This project is available under the MIT License. See [LICENSE](LICENSE) for further details.
