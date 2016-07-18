package org.gvaireth.model;

import java.util.List;

public class Statistics {
	private WorkoutTotalCrudData total;
	private List<WorkoutTotalCrudData> totalPerSport;
	private List<WorkoutCrudData> longest;

	public WorkoutTotalCrudData getTotal() {
		return total;
	}

	public void setTotal(WorkoutTotalCrudData total) {
		this.total = total;
	}

	public List<WorkoutTotalCrudData> getTotalPerSport() {
		return totalPerSport;
	}

	public void setTotalPerSport(List<WorkoutTotalCrudData> totalPerSport) {
		this.totalPerSport = totalPerSport;
	}

	@Override
	public String toString() {
		return "Statistics [total=" + total + ", totalPerSport=" + totalPerSport + "]";
	}

	public List<WorkoutCrudData> getGreatestDuration() {
		return longest;
	}

	public void setLongest(List<WorkoutCrudData> longest) {
		this.longest = longest;
	}

}
