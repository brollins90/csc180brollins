package lab4_rollins_blake;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class ElevatorController {
    Scanner scan;
    LinkedBlockingQueue<Integer> elevatorQueue;
    Elevator e1;
    Thread e1t;
    boolean elevatorRunning;

    ElevatorController() {
        scan = new Scanner(System.in);
        elevatorQueue = new LinkedBlockingQueue<>();
        e1 = new Elevator(elevatorQueue);
        elevatorRunning = true;
    }

    void createElevator() {
        e1t = new Thread(e1);
        e1t.start();
        while (elevatorRunning) {
            promptForFloor();
        }
        stopElevator();
    }

    void stopElevator() {
        System.out.println("Elevator asked to stop.");
        e1t.interrupt();
    }

    void addFloor(int floorNum) {

        if (floorNum == e1.getCurrentFloor()) {
            System.out.println("Already on floor " + floorNum);
        } else if (elevatorQueue.contains(floorNum)) {
            System.out.println("Queue already contains floor " + floorNum);
        } else {
            System.out.println("Added: " + floorNum);
            this.elevatorQueue.add(floorNum);
        }

    }

    void promptForFloor() {
        int newFloorNum = getFloorNumber();
        if (newFloorNum == 0) {
            elevatorRunning = false;
        } else {
            addFloor(newFloorNum);
        }

    }

    int getFloorNumber() {

        boolean isValid = false;
        int returnNum = -1;
        System.out.println("What floor would you like to go to (1-10)?  Type 0 to stop.");

        do {
            try {
                String tempLine = readLine();
                int tempInt = Integer.parseInt(tempLine);
                if (tempInt > -1 && tempInt < 11) {
                    returnNum = tempInt;
                    isValid = true;
                }
            } catch (NumberFormatException e) {

            }
            if (!isValid) {
                System.out.println("The requested floor was not valid.  Try again.");
            }
        } while (!isValid);
        return returnNum;
    }

    String readLine() {
        return scan.next();
    }
}
