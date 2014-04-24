package com.github.phlank.musicplayer.model.comparable;

import java.util.Comparator;

import com.github.phlank.musicplayer.model.Song;

public class SongDiskNumberComparable implements Comparator<Song> {

	@Override
	public int compare(Song o1, Song o2) {
		return o1.getDiskNumber().compareTo(o2.getDiskNumber());
	}

}
