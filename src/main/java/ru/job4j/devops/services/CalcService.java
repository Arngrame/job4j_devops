package ru.job4j.devops.services;

import ru.job4j.devops.models.User;

public interface CalcService {

    Long add(User user, int first, int second);

}
