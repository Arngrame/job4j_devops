package ru.job4j.devops.services;

import org.springframework.stereotype.Service;
import ru.job4j.devops.enums.CalcEventType;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;

@Service
public class CalcServiceImpl implements CalcService {

    private final CalcEventRepository calcEventRepository;

    public CalcServiceImpl(CalcEventRepository calcEventRepository) {
        this.calcEventRepository = calcEventRepository;
    }

    @Override
    public Long add(User user, int first, int second) {
        int result = first + second;

        CalcEvent calcEvent = new CalcEvent();
        calcEvent.setUserId(user.getId());
        calcEvent.setType(CalcEventType.ADDITION);
        calcEvent.setFirstArg(first);
        calcEvent.setSecondArg(second);
        calcEvent.setResult(result);

        CalcEvent savedEntity = calcEventRepository.save(calcEvent);

        return savedEntity.getId();
    }
}
