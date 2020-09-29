package ua.kpi.iasa.IASA_Organiser.model;

import java.util.Comparator;

public class CalendarRecording implements Comparator<CalendarRecording>{
	private Event event;
	private boolean expiredFlag;
	
	public CalendarRecording(Event event) {
		this.event = event;
		this.expiredFlag = false; //checks if event is expired
	}
	
	public Event getEvent() {
		return this.event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public boolean getExpiredFlag() {
		return this.expiredFlag;
	}
	
	public void setExpiredFlag(boolean expFlag) {
		this.expiredFlag = expFlag;
	}
	
	@Override
	public int compare(CalendarRecording rec1, CalendarRecording rec2) {
		int yearDif = rec1.event.getDate().getYear() - rec2.event.getDate().getYear();
		int dayDif = rec1.event.getDate().getDayOfYear() - rec2.event.getDate().getDayOfYear();
		int hourDif = rec1.event.getTime().getHour() - rec2.event.getTime().getHour();
		int minuteDif = rec1.event.getTime().getMinute() - rec2.event.getTime().getMinute();
		int secondDif = rec1.event.getTime().getSecond() - rec2.event.getTime().getSecond();
		if(yearDif != 0) {
			return yearDif;
		}
		else if(dayDif != 0) {
			return dayDif;
		}
		else if(hourDif != 0){
			return hourDif;
		}
		else if(minuteDif != 0) {
			return minuteDif;
		}
		else {
			return secondDif;
		}
	}
}
