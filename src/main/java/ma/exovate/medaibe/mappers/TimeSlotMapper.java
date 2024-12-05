package ma.exovate.medaibe.mappers;

import ma.exovate.medaibe.dtos.TimeSlotDTO;
import ma.exovate.medaibe.entities.Timeslot;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TimeSlotMapper {
    public TimeSlotDTO fromTimeSlot(Timeslot timeSlot) {
        TimeSlotDTO timeSlotDto = new TimeSlotDTO();
        BeanUtils.copyProperties(timeSlot, timeSlotDto);
        return timeSlotDto;
    }

    public Timeslot fromTimeSlotDto(TimeSlotDTO timeSlotDto) {
        Timeslot timeSlot = new Timeslot();
        BeanUtils.copyProperties(timeSlotDto, timeSlot);
        return timeSlot;
    }
}
