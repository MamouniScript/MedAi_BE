package ma.exovate.medaibe.mappers;

import ma.exovate.medaibe.dtos.ScheduleDto;
import ma.exovate.medaibe.entities.Schedule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

    public ScheduleDto fromSchedule(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();
        BeanUtils.copyProperties(schedule, scheduleDto);
        return scheduleDto;
    }

    public Schedule fromScheduleDto(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDto, schedule);
        return schedule;
    }
}
