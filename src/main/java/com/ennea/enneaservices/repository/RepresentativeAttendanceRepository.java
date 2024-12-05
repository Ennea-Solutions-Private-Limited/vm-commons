package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.RepresentativeAttendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface RepresentativeAttendanceRepository extends CrudRepository<RepresentativeAttendance, Long> {

    @Query("select ra from RepresentativeAttendance ra where DATE(ra.date) between :startDate and :endDate" +
           "  and ra.representative.id = :representativeId")
    List<RepresentativeAttendance> findAllByDateRangeAndRepresentativeId(LocalDate startDate, LocalDate endDate,
                                                                         Long representativeId);
}
