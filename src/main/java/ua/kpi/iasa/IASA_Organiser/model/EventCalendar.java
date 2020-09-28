package ua.kpi.iasa.IASA_Organiser.model;

import java.util.GregorianCalendar; 
import java.util.Arrays;

public class EventCalendar {
	private CalendarRecording [] recordings;
	private GregorianCalendar gCal;
	
	public EventCalendar(){
		this.recordings = new CalendarRecording [100];
		this.gCal = new GregorianCalendar();
	}
	
	public void sortRecByDateTime() {
		Arrays.sort(this.recordings);
	}
	
	public CalendarRecording getFirstRecording() {
		return this.recordings[0];
	}
	
	public CalendarRecording[] getRecordingsBeforeDate(int year, int month, int day) {
		this.sortRecByDateTime();
		int length = 0;
		int evYear = (this.recordings[length]).getEvent().getDate().getYear();
		int evMonth = (this.recordings[length]).getEvent().getDate().getMonthValue();
		int evDay = (this.recordings[length]).getEvent().getDate().getDayOfMonth();
		while((evYear <= year) && (evMonth <= month) && (evDay <= day)) {
			length++; // count amount all recordings, which are going to happen before some date 
		}
		
		CalendarRecording[] recBeforeList = new CalendarRecording[length];
		
		for(int i = 0; i < length; i++) {
			recBeforeList[i] = this.recordings[i]; // write all recordings to array
		}
		
		return recBeforeList;
		
	}
	
	

	
	public boolean updateCalendar(Event [] eventList) {
		
		this.recordings = new CalendarRecording[eventList.length];
		
		for(int i = 0; i < eventList.length; i++) { // iterate over all the events
			this.recordings[i] = new CalendarRecording(eventList[i]); // create recordings from events
		}
		
		for(int i = 0; i < eventList.length; i++) {
			
		}
		
		this.sortRecByDateTime();
		
		return  true;
	}
}
