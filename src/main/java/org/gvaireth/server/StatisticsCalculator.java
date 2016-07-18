package org.gvaireth.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gvaireth.model.Statistics;
import org.gvaireth.model.WorkoutCrudData;
import org.gvaireth.model.WorkoutTotalCrudData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moomeen.endo2java.model.Sport;

@Component
public class StatisticsCalculator {

	@Autowired
	private WorkoutConverter workoutConverter;

	Statistics getStatistics(List<WorkoutCrudData> workouts) {
		Statistics statistics = new Statistics();
		statistics.setTotal(getTotal(workouts, Sport.ALL));
		Set<Sport> sportsPresent = getSportsPresent(workouts);
		List<WorkoutTotalCrudData> totalPerSport = new ArrayList<>();
		for (Sport sport : sportsPresent) {
			totalPerSport.add(getTotal(workouts, sport));
		}
		Collections.sort(totalPerSport);
		Collections.reverse(totalPerSport);
		statistics.setTotalPerSport(totalPerSport);
		List<WorkoutCrudData> greatestDuration = getLongest(workouts, sportsPresent);
		Collections.sort(greatestDuration, new Comparator<WorkoutCrudData>() {

			@Override
			public int compare(WorkoutCrudData o1, WorkoutCrudData o2) {
				return (int) (o2.getDuration() - o1.getDuration());
			}
		});
		statistics.setLongest(greatestDuration);
		System.out.println(statistics);
		return statistics;
	}

	private Set<Sport> getSportsPresent(List<WorkoutCrudData> workouts) {
		Set<Sport> sportsPresent = new HashSet<>();
		for (WorkoutCrudData workout : workouts) {
			sportsPresent.add(workout.getSportEnum());
		}
		return sportsPresent;
	}

	private List<WorkoutCrudData> getLongest(List<WorkoutCrudData> workouts, Set<Sport> sportsPresent) {
		Map<Sport, WorkoutCrudData> perSportMax = new HashMap<>();
		for (WorkoutCrudData workout : workouts) {
			if (!perSportMax.containsKey(workout.getSportEnum())) {
				perSportMax.put(workout.getSportEnum(), workout);
			} else {
				Long curDuration = workout.getDuration();
				Long maxDuration = perSportMax.get(workout.getSportEnum()).getDuration();
				if (curDuration > maxDuration) {
					perSportMax.put(workout.getSportEnum(), workout);
				}
			}
		}
		return new ArrayList<>(perSportMax.values());
	}

	private WorkoutTotalCrudData getTotal(List<WorkoutCrudData> workouts, Sport sport) {
		WorkoutTotalCrudData total = new WorkoutTotalCrudData();
		Integer workoutsNo = 0;
		Long duration = 0l;
		Double distance = 0.0;
		Long calories = 0l;
		for (WorkoutCrudData workout : workouts) {
			if (workout.getSportEnum() == sport || sport == Sport.ALL) {
				workoutsNo++;
				if (workout.getDuration() != null) {
					duration += workout.getDuration();
				}
				if (workout.getDistance() != null) {
					distance += workout.getDistance();
				}
				if (workout.getCalories() != null) {
					calories += workout.getCalories();
				}
			}
		}
		total.setSportEnum(sport);
		total.setWorkoutsNo(workoutsNo);
		total.setDuration(duration);
		total.setDistance(workoutConverter.trimDouble(distance));
		total.setCalories(calories);
		return total;
	}

}
