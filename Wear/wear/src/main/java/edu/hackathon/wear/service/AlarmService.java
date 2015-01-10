package edu.hackathon.wear.service;

/**
 * Created by duby on 1/10/15.
 */
public class AlarmService {

    // static singleton service
    private static AlarmService alarmService = null;

    // instance variables
    private int secondsInterval = 15;

    /**
     * singleton accessor
     *
     * @return
     */
    public static AlarmService getAlarmService() {
        if (alarmService == null) {
            alarmService = new AlarmService();
        }

        return alarmService;
    }

    public int getSecondsInterval() {
        return secondsInterval;
    }

    public void setSecondsInterval(int secondsInterval) {
        this.secondsInterval = secondsInterval;
    }
}
