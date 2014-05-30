package lab4_rollins_blake;

import java.util.concurrent.LinkedBlockingQueue;

public class Elevator implements Runnable {

    final LinkedBlockingQueue<Integer> elevatorQueue;
    ElevatorStatus currentStatus;
    int currentFloor;

    public Elevator(LinkedBlockingQueue<Integer> q) {
        this.elevatorQueue = q;
        currentStatus = ElevatorStatus.WAITING_FOR_REQUEST;
        this.currentFloor = 1;
    }

    @Override
    public void run() {
        boolean notified = false;
        try {
            while (true) {
               // System.err.println(elevatorQueue.size());
                if (!notified && elevatorQueue.size() == 0) {
                    notified = true;
                    currentStatus = ElevatorStatus.WAITING_FOR_REQUEST;
                    System.err.println("Waiting for request.");
                } else {
                    notified = false;
                    moveElevator(elevatorQueue.take());
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Elevator thread interupted.");
        } catch (IllegalMonitorStateException e) {
            System.err.println("Elevator thread stopped.");
        }
    }
    
    public synchronized ElevatorStatus getStatus() {
        return this.currentStatus;
    }
    
    public synchronized int getCurrentFloor() {
        return currentFloor;
    }

    private void moveElevator(Integer take) throws InterruptedException {
        currentStatus = ElevatorStatus.IN_MOTION;
        System.err.println("Moving to " + take);
        Thread.sleep(3000);
        System.err.println("Moved to " + take);
        this.currentFloor = take;
    }
}
