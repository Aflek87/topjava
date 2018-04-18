package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController extends AbstractMealController{

    @GetMapping(value = "/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:meals";
    }

    @GetMapping(value = "/create")
    public String create(HttpServletRequest request) {
        final Meal meal =super.create( new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "Ужин", 1000));
        request.setAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping(value = "/update")
    public String update(HttpServletRequest request) {
        request.setAttribute("meal", super.get(getId(request)));
        return "mealForm";
    }

    @PostMapping(value = "/save")
    public String save(HttpServletRequest request) {
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (request.getParameter("id").isEmpty()) {
            super.create(meal);
        } else {
            super.update(meal, getId(request));
        }
        return "redirect:meals";
    }

    @PostMapping(value = "/filter")
    public String filter(HttpServletRequest request) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));

        List<MealWithExceed> meals = super.getBetween(startDate, startTime, endDate, endTime);
        request.setAttribute("meals", meals);
        return "meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
