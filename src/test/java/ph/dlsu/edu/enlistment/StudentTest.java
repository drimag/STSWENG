package ph.dlsu.edu.enlistment;

import java.util.Collections;
import java.util.List;

//import org.apache.commons.lang3.StringUtils;
import static org.junit.jupiter.api.Assertions.*;
import static ph.dlsu.edu.enlistment.Days.*;
import static ph.dlsu.edu.enlistment.Period.*;
import org.junit.jupiter.api.Test;


class StudentTest {
    final Schedule MTH_0830 = new Schedule(MTH, H0830);
    
    Student newDefaultStudent(){
        return new Student(1);
    }

    @Test
    void enlist_same_student_in_2_sections_with_no_schedule_conflict(){
        //Given a student w/ no sections enlisting in 2 sections with no sched conflict
        Student student = newDefaultStudent();
        Section sec1 = new Section("A", MTH_0830);
        Section sec2 = new Section("B", new Schedule(TF, H1130));

        //When the student enlists in both
        student.enlist(sec1);
        student.enlist(sec2);

        //Then the 2 sections should be found in the student & only 2 sections
        var sections = student.getSections();
        assertAll(
                () -> assertTrue(sections.containsAll(List.of(sec1,sec2))),
                () -> assertEquals(2, sections.size())
        );
    }

    @Test
    void enlist_same_student_in_2_sections_with_same_schedule(){
        /***
         * Test driven allows you to not yander
         */
        //Given a w/ no section and 2 sections with the same sched

        Student student = newDefaultStudent();
        Section sec1 = new Section("A", MTH_0830);
        Section sec2 = new Section("B", MTH_0830);

        //When student enlists in both sections
        student.enlist(sec1);

        //Then an exception will be thrown at the second enlistment
        assertThrows(RuntimeException.class, () -> student.enlist(sec2));
    }

}
