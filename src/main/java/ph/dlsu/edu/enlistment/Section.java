package ph.dlsu.edu.enlistment;

/**
 * How to know if a library is trusted
 * 1. know how many projects are using it
 * 2.
 */

/**
 * importing the apache lib
 * you can do this
 * import org.apache.commons.lang3.*;
 *
 * or make a static import
 */

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.StringUtils.*;

class Section {
    private final Schedule schedule;
    private final String sectionId;


    Section(String sectionId, Schedule schedule) {
        notBlank(sectionId, "sectionID cannot be null, empty or whitespace");
        isTrue(isAlphanumeric(sectionId), "SectionId must be alphanumeric, was:" + sectionId);
        requireNonNull(schedule);

        this.sectionId = sectionId;
        this.schedule = schedule;
    }

    void checkForConflict(Section other){
        if(this.schedule.equals(other.schedule)){
            throw new ScheduleConflictException("This section " + this +
                    " has conflict with section " + other +
                    " having same schedule at " + schedule);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(sectionId, section.sectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionId);
    }
    @Override
    public String toString(){
        return sectionId;
    }
}
