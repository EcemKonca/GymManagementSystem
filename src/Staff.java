import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Staff extends PersonalDetails {  // Inheritance -> Subclass for detailed staff's information

    private String jobRole;
    private String password;
    private String workDays;

    private String workHours;

    private ArrayList<String> lessonTimeList = new ArrayList<>();

    private HashMap<String, ArrayList<String>> lessonTimeListWithDays = new HashMap<>();

    public ArrayList<String> getLessonTimeList() {
        return lessonTimeList;
    }
    public void setLessonTimeListWithDays(HashMap<String, ArrayList<String>> lessonTimeListWithDays) {
        this.lessonTimeListWithDays = lessonTimeListWithDays;
    }

    public HashMap<String, ArrayList<String>> getLessonTimeListWithDays() {
        return lessonTimeListWithDays;
    }

    // Constructor
    public Staff(String name, String lastname, String phoneNo, String Password, String address, String jobRole, String workDays, String workHours) {
        super(name, lastname, phoneNo, address);
        this.jobRole = jobRole;
        this.workDays = workDays;
        this.workHours = workHours;
        this.password = Password;

    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String Password) {
        this.password = Password;
    }

    public void schedulePrivateLesson(LocalDateTime lessonStartDateTime, LocalDateTime lessonEndDateTime, String day) {

        String formattedDay =  day.substring(0,1).toUpperCase() + day.substring(1).toLowerCase();

        String[] scheduleParts = workHours.split("-");
        LocalTime scheduleStartTime = LocalTime.parse(scheduleParts[0].trim());
        LocalTime scheduleEndTime = LocalTime.parse(scheduleParts[1].trim());

        LocalTime lessonStartTime = lessonStartDateTime.toLocalTime();
        LocalTime lessonEndTime = lessonEndDateTime.toLocalTime();

        String[] workDaysArray = workDays.split(",");

        if (Arrays.stream(workDaysArray).toList().contains(formattedDay)) {

            if (!(lessonTimeListWithDays.containsKey(formattedDay))) {

                lessonTimeList = new ArrayList<>();

                if (lessonStartTime.isBefore(scheduleStartTime) || lessonEndTime.isAfter(scheduleEndTime)) {
                    System.out.println("Sorry, you cannot schedule a private lesson outside the trainer's working hours.");
                }
                else {

                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String formattedStartDate = lessonStartDateTime.format(myFormatObj);
                    String formattedEndDate = lessonEndDateTime.format(myFormatObj);

                    String formattedLessonTime = formattedStartDate.concat("-" + formattedEndDate);

                    lessonTimeList.add(formattedLessonTime);
                    lessonTimeListWithDays.put(formattedDay, lessonTimeList);
                    System.out.println("Private lesson scheduled for " + day + formattedLessonTime);

                }
            }
            else {
                lessonTimeList = lessonTimeListWithDays.get(formattedDay);

                for (String s : lessonTimeListWithDays.get(formattedDay)) {
                    String[] scheduledLessons = s.split("-");
                    LocalTime lessonStart = LocalTime.parse(scheduledLessons[0].trim());
                    LocalTime lessonEnd = LocalTime.parse(scheduledLessons[1].trim());
                    if (lessonStartTime.isBefore(lessonEnd) && lessonEndTime.isAfter(lessonStart)) {
                        System.out.println("Sorry, you cannot schedule a private lesson during this time as it conflicts with an existing lesson.");
                        return;
                    }
                }

                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedStartDate = lessonStartDateTime.format(myFormatObj);
                String formattedEndDate = lessonEndDateTime.format(myFormatObj);

                String formattedLessonTime = formattedStartDate.concat("-" + formattedEndDate);

                lessonTimeList.add(formattedLessonTime);
                lessonTimeListWithDays.put(formattedDay, lessonTimeList);
                System.out.println("Private lesson scheduled for " + day + formattedLessonTime);

            }

        }
        else {
            System.out.println("Trainer does not work that day..");
        }
    }
}
