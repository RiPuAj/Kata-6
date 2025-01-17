package es.ulpgc.software.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

public class WorkCalendar {
    private final Set<DayOfWeek> workingDays = Set.of(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY
    );

    public Iterator<LocalDate> iteratorFor(LocalDate initDate) {
        return new Iterator<>() {
            private LocalDate current = initDate;

            public boolean hasNext() {
                return true;
            }

            public LocalDate next() {
                var next = current.plusDays(1);
                while(!isWorkingDay(next)) next = current.plusDays(1);
                return next;
            }
        };
    }

    private boolean isWorkingDay(LocalDate next) {
        return workingDays.contains(next.getDayOfWeek());
    }
}
