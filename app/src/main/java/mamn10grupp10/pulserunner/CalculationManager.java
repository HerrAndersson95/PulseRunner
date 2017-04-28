package mamn10grupp10.pulserunner;

/**
 * Created by Annie on 2017-04-05.
 */

public class CalculationManager {
    double totalDistance;
    double currentDistance;


    public CalculationManager(){
        totalDistance = 0;
        currentDistance = 0;
    }

    public double getTotalLength(){
        return totalDistance;
    }

    public double getDistance(long oldX, long oldY, long newX, long newY){     // generally used geo measurement function
            double radiusEarth = 6378.137;                                                // Radius of earth in KM
            double diffX = newX * Math.PI / 180 - oldX * Math.PI / 180;
            double diffY = newY * Math.PI / 180 - oldY * Math.PI / 180;
            double a = Math.sin(diffX/2) * Math.sin(diffX/2) +
                    Math.cos(oldX * Math.PI / 180) * Math.cos(newX * Math.PI / 180) *
                            Math.sin(diffY/2) * Math.sin(diffY/2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            double d = radiusEarth * c;
            currentDistance =  d * 1000; // meters
            return currentDistance;
    }

    public void addCurrentDistance(){
        totalDistance = totalDistance + currentDistance;
    }

    public double getCurrentSpeed(double time) {
        double meterpersecound = currentDistance / time;
        double kilometerperhour = meterpersecound * 3.6;
        return kilometerperhour;
    }
}