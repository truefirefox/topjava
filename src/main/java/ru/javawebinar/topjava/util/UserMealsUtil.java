package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        List<UserMealWithExceed> meals = new ArrayList<>();

        for (UserMeal meal : mealList) {
            if (meal.getDateTime().toLocalTime().compareTo(startTime) > 0 &&
                    meal.getDateTime().toLocalTime().compareTo(endTime) < 0) {
                meals.add(new UserMealWithExceed(meal.getDateTime(),
                        meal.getDescription(), meal.getCalories(), meal.getCalories() > caloriesPerDay));
            }

        }

        return meals;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStreamAPI(List<UserMeal> mealList, LocalTime startTime,
                                                                            LocalTime endTime, int caloriesPerDay) {

        return mealList.stream()
                .filter(u -> u.getDateTime().toLocalTime().compareTo(startTime) > 0 &&
                        u.getDateTime().toLocalTime().compareTo(endTime) < 0)
                .map(u -> new UserMealWithExceed(u.getDateTime(), u.getDescription(),
                        u.getCalories(), u.getCalories() > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
