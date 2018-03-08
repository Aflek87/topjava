package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

//        request.getRequestDispatcher("/users.jsp").forward(request, response);
        Map<LocalDate, Integer> caloriesSumByDate = MealsUtil.meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );
         MealsUtil.meals.stream()
                .map(meal -> MealsUtil.createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > 2000))
                .collect(toList());
        request.setAttribute("listMeals", MealsUtil.meals.stream()
                .map(meal -> MealsUtil.createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > 2000))
                .collect(toList()));
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
