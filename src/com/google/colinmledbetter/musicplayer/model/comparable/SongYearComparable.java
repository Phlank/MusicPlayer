package com.google.colinmledbetter.musicplayer.model.comparable;

import java.util.Comparator;

import com.google.colinmledbetter.musicplayer.model.Song;

public class SongYearComparable implements Comparator<Song> {

	@Override
	public int compare(Song o1, Song o2) {
		return o1.getYear().compareTo(o2.getYear());
	}

}
