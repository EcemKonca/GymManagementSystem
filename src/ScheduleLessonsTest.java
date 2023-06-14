import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ScheduleLessonsTest {

    @Test
    public void testSchedulePrivateLesson_Positive() {

        String workDays = "Monday";
        String workHours = "09:00:00 - 18:00:00";
        Staff staff = new Staff("John", "Doe", "1234567890", "123","123 Main St", "Trainer", workDays, workHours);


        LocalDateTime lessonStartDateTime = LocalDateTime.of(2023, 5, 25, 14, 0);
        LocalDateTime lessonEndDateTime = LocalDateTime.of(2023, 5, 25, 15, 0);
        String day = "Monday";

        staff.schedulePrivateLesson(lessonStartDateTime, lessonEndDateTime, day);

        assertEquals(1, staff.getLessonTimeListWithDays().get(day).size());
    }

    @Test
    public void testSchedulePrivateLesson_Negative() {

        String workDays = "Monday";
        String workHours = "09:00:00 - 18:00:00";
        Staff staff = new Staff("John", "Doe", "1234567890", "123","123 Main St", "Trainer", workDays, workHours);

        LocalDateTime lessonStartDateTime = LocalDateTime.of(2023, 5, 25, 8, 0);
        LocalDateTime lessonEndDateTime = LocalDateTime.of(2023, 5, 25, 9, 0);
        String day = "Saturday";


        staff.schedulePrivateLesson(lessonStartDateTime, lessonEndDateTime, day);

        assertFalse(staff.getLessonTimeListWithDays().containsKey(day));
    }
}
