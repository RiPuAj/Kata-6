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

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public LocalDate next() {
                var next = current.plusDays(1);
                while(!isWorkingDay(next)) next = next.plusDays(1);
                current = next;
                return current;
            }
        };
    }

    private boolean isWorkingDay(LocalDate day) {
        return workingDays.contains(day.getDayOfWeek());
    }
}
