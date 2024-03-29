package ru.mylov.vacationCalculator.services;

import org.springframework.stereotype.Service;
import ru.mylov.vacationCalculator.exceptions.EmptyVacationPeriodException;
import ru.mylov.vacationCalculator.exceptions.WrongDateFormatException;
import ru.mylov.vacationCalculator.exceptions.WrongVacationPeriodException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalculationService {
    private int holidaysCounter;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    Set<String> holidays = Stream.of(
                    "01.01",
                    "02.01",
                    "03.01",
                    "04.01",
                    "05.01",
                    "06.01",
                    "07.01",
                    "08.01",
                    "23.02",
                    "08.03",
                    "01.05",
                    "09.05",
                    "12.06",
                    "04.11")
            .collect(Collectors.toCollection(HashSet::new));

    public String calculateVacationPay(int averageSalary, String startDate, String endDate) {
        String dateFormatRegExp = "(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)";
        if (startDate.isEmpty() || endDate.isEmpty()) {
            throw new EmptyVacationPeriodException("Vacation start and end dates must not be empty");
        } else if (!startDate.matches(dateFormatRegExp) || !endDate.matches(dateFormatRegExp)) {
            throw new WrongDateFormatException("Wrong date format in request. You should use 'dd.mm.yyyy' date format");
        } else {
            LocalDate startVacationDate = LocalDate.parse(startDate, formatter);
            LocalDate endVacationDate = LocalDate.parse(endDate, formatter);

            if (endVacationDate.isBefore(startVacationDate)) {
                throw new WrongVacationPeriodException("Start vacation date must be earlier than end vacation date");
            } else {
                startVacationDate.datesUntil(endVacationDate).forEach(this::holidayCheck);
                double salary = averageSalary / 29.3 * ((ChronoUnit.DAYS.between(startVacationDate, endVacationDate) + 1) - holidaysCounter);
                String formattedSalary = new DecimalFormat("#0.00").format(salary);
                holidaysCounter = 0;
                return formattedSalary;
            }
        }
    }

    public int holidayCheck(LocalDate checkedDate) {
        String checkedFormattedDate = checkedDate.format(formatter);
        if (holidays.contains(checkedFormattedDate.substring(0, 5))) {
            holidaysCounter++;
        }
        return holidaysCounter;
    }
}
