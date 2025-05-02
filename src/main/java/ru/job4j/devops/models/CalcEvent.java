package ru.job4j.devops.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.devops.enums.CalcEventType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "calc_event")
public class CalcEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_arg")
    private Integer firstArg;

    @Column(name = "second_arg")
    private Integer secondArg;

    @Column(name = "type")
    private CalcEventType type;

    @Column(name = "result")
    private Integer result;

    @Column(name = "create_date", insertable = false, updatable = false)
    private LocalDate createDate;
}
