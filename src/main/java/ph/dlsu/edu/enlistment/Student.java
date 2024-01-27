package ph.dlsu.edu.enlistment;

import org.apache.commons.lang3.BooleanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.isTrue;
import static java.util.Objects.requireNonNull;

class Student {
    private final int studentNumber;
    private final Collection <Section> sections;
    Student(int studentNumber, Collection<Section> sections) {
        isTrue(studentNumber >= 0, "studentNumber must be non-negative, was: " + studentNumber);
        requireNonNull(sections, "sections cannot be null.");
        this.studentNumber = studentNumber;

        this.sections = new HashSet<>(sections);
        isTrue(!this.sections.contains(null), "sections cannot contain null");
    }


    Student (int studentNumber){
        if (studentNumber > 0){
            this.studentNumber = studentNumber;
            this.sections = new HashSet<>();
        }
        else{
            throw new IllegalArgumentException("Student number must be non negative");
        }
    }

    void enlist (Section newSection){
        Objects.requireNonNull(newSection, "section cannot be null.");
        sections.forEach(currSection -> {
            currSection.checkForConflict(newSection);
        });
        sections.add(newSection);
    }

    Collection<Section> getSections(){
        return new ArrayList<>(sections);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentNumber == student.studentNumber && Objects.equals(sections, student.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNumber, sections);
    }

    @Override
    public String toString() {
        return "Student# " + studentNumber;
    }
}

