package DB;

public class Schedule {
    private int year;
    private int month;
    private int day;
    private String description;
    private String startTime;
    private String endTime;
    private String username;

    public Schedule(int year, int month, int day, String description, String startTime, String endTime, String username) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.username = username;
    }

    // Getter 및 Setter 메서드도 필요에 따라 추가할 수 있습니다.

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return startTime + " ~ " + endTime + " " + description;
    }
}
