public class Process {

    private int ID;
    private boolean workWithDevices;
    private int timeBeforeWorkWithDevices;
    private int timeWorkWithDevices;
    private int timeAfterWorkWithDevices;
    int workTime = 0;
    private int timeSegment = 1000;

    Process(int ID, boolean workWithDevices, int timeBeforeWorkWithDevices, int timeWorkWithDevices, int timeAfterWorkWithDevices) {
        this.ID = ID;
        this.workWithDevices = workWithDevices;
        this.timeBeforeWorkWithDevices = timeBeforeWorkWithDevices;
        this.timeWorkWithDevices = timeWorkWithDevices;
        this.timeAfterWorkWithDevices = timeAfterWorkWithDevices;
    }

    public String performProcessWithoutBlock() {
        workTime = 0;
        System.out.println("Процесс " + ID + " начинает работу");
        if (timeBeforeWorkWithDevices != 0) {
            if (timeBeforeWorkWithDevices > timeSegment - workTime) {
                timeBeforeWorkWithDevices -= timeSegment - workTime;
                workTime = timeSegment;
                return "Процесс " + ID + " приостановлен после " + workTime + " единиц времени";
            } else {
                workTime += timeBeforeWorkWithDevices;
                timeBeforeWorkWithDevices = 0;
            }
        }
        if (workWithDevices && timeWorkWithDevices != 0) {
            if (timeWorkWithDevices > timeSegment - workTime) {
                timeWorkWithDevices -= timeSegment - workTime;
                workTime = timeSegment;
                return "Процесс " + ID + " приостановлен после " + workTime + " единиц времени";
            } else {
                workTime += timeWorkWithDevices;
                timeWorkWithDevices = 0;
            }
        }
        if (timeAfterWorkWithDevices != 0) {
            if (timeAfterWorkWithDevices > timeSegment - workTime) {
                timeAfterWorkWithDevices -= timeSegment - workTime;
                workTime = timeSegment;
                return "Процесс " + ID + " приостановлен после " + workTime + " единиц времени";
            } else {
                workTime += timeAfterWorkWithDevices;
                timeAfterWorkWithDevices = 0;
            }
        }
        return "Процесс " + ID + " выполнен после " + workTime + " единиц времени";
    }

    public String performProcessWithBlock() {
        workTime = 0;
        System.out.println("Процесс " + ID + " начинает работу");
        if (timeBeforeWorkWithDevices != 0) {
            if (timeBeforeWorkWithDevices > timeSegment - workTime) {
                timeBeforeWorkWithDevices -= timeSegment - workTime;
                workTime = timeSegment;
                return "Процесс " + ID + " приостановлен после " + workTime + " единиц времени";
            } else {
                workTime += timeBeforeWorkWithDevices;
                timeBeforeWorkWithDevices = 0;
            }
        }
        if (workWithDevices && timeWorkWithDevices != 0) {
            return "Процесс " + ID + " приостановлен после " + workTime + " единиц времени";
        }
        if (timeAfterWorkWithDevices != 0) {
            if (timeAfterWorkWithDevices > timeSegment - workTime) {
                timeAfterWorkWithDevices -= timeSegment - workTime;
                workTime = timeSegment;
                return "Процесс " + ID + " приостановлен после " + workTime + " единиц времени";
            } else {
                workTime += timeAfterWorkWithDevices;
                timeAfterWorkWithDevices = 0;
            }
        }
        return "Процесс " + ID + " выполнен после " + workTime + " единиц времени";
    }

    public String performProcessWithBreaking(int workTimeBeforeBreak) {
        workTime = 0;
        System.out.println("Процесс " + ID + " начинает работу");
        if (timeBeforeWorkWithDevices != 0) {
            if (timeBeforeWorkWithDevices > workTimeBeforeBreak - workTime) {
                timeBeforeWorkWithDevices -= workTimeBeforeBreak - workTime;
                workTime = workTimeBeforeBreak;
                return "Процесс " + ID + " прерван после " + workTime + " единиц времени";
            } else {
                workTime += timeBeforeWorkWithDevices;
                timeBeforeWorkWithDevices = 0;
            }
        }
        if (workWithDevices && timeWorkWithDevices != 0) {
            timeWorkWithDevices = 0;
            return "Процесс " + ID + " прерван после " + workTime + " единиц времени";
        }
        if (timeAfterWorkWithDevices != 0) {
            if (timeAfterWorkWithDevices > workTimeBeforeBreak - workTime) {
                timeAfterWorkWithDevices -= workTimeBeforeBreak - workTime;
                workTime = workTimeBeforeBreak;
                return "Процесс " + ID + " прерван после " + workTime + " единиц времени";
            } else {
                workTime += timeAfterWorkWithDevices;
                timeAfterWorkWithDevices = 0;
            }
        }
        return "Процесс " + ID + " выполнен после " + workTime + " единиц времени";
    }

    public String extensionPerformProcessWithBlock() {
        System.out.println("Процесс " + ID + " продолжает работу\n");
        if (timeAfterWorkWithDevices != 0) {
            if (timeAfterWorkWithDevices > timeSegment - workTime) {
                timeAfterWorkWithDevices -= timeSegment - workTime;
                workTime = timeSegment;
                return "Процесс " + ID + " приостановлен после " + workTime + " единиц времени";
            } else {
                workTime += timeAfterWorkWithDevices;
                timeAfterWorkWithDevices = 0;
            }
        }

        return "Процесс " + ID + " выполнен после " + workTime + " единиц времени";
    }

    public int getTotalTime() {
        if (workWithDevices) {
            return timeBeforeWorkWithDevices + timeWorkWithDevices + timeAfterWorkWithDevices;
        }
        return timeBeforeWorkWithDevices + timeAfterWorkWithDevices;
    }

    public String getWorkWithDevices() {
        if (workWithDevices) {
            return "взаимодействует во время выполнения с устройством ввода/вывода";
        }
        return "не взаимодействует во время выполнения с устройством ввода/вывода";
    }

    public String getTimeWorkWithDevicesToPrint() {
        if (workWithDevices) {
            return " и оно составляет " + timeWorkWithDevices;
        }
        return "";
    }

    public int getID() {
        return ID;
    }

    public void setTimeWorkWithDevices() {
        timeWorkWithDevices = 0;
    }

    public int getTimeWorkWithDevices() {
        return timeWorkWithDevices;
    }

    public int getWorkTime() {
        return workTime;
    }
}
