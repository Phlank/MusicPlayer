package com.google.colinmledbetter.musicplayer.model;

public class MusicPlayerUtils {

	private static final int SECONDS_PER_MINUTE = 60;
	private static final int MINUTES_PER_HOUR = 60;

	public static String secondsToFormattedString(int numberOfSeconds) {
		int seconds = numberOfSeconds % SECONDS_PER_MINUTE;
		int minutes = (numberOfSeconds / SECONDS_PER_MINUTE) % MINUTES_PER_HOUR;
		int hours = numberOfSeconds / (SECONDS_PER_MINUTE * MINUTES_PER_HOUR);
		String formattedString = "";
		formattedString = formattedString + hours + ":";
		if (minutes < 10) {
			formattedString = formattedString + "0" + minutes + ":";
		} else {
			formattedString = formattedString + minutes + ":";
		}
		if (seconds < 10) {
			formattedString = formattedString + "0" + seconds;
		} else {
			formattedString = formattedString + seconds;
		}
		return formattedString;
	}

}
