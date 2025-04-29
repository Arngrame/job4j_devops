package ru.job4j.devops.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.services.CalcService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CalcEventRepositoryTest extends ContainersConfig {

    @Autowired
    private CalcEventRepository calcEventRepository;

    @Autowired
    private CalcService calcService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenAdditionPositive() {
        var user = new User();
        user.setName("Job4j");
        userRepository.save(user);

        Long eventId = calcService.add(user, 10, 20);
        Optional<CalcEvent> calcEvent = calcEventRepository.findById(eventId);

        assertThat(calcEvent).isPresent();
        assertThat(calcEvent.get().getResult()).isEqualTo(30);
    }
}
