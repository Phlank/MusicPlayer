package com.github.phlank.musicplayer.model.comparable;

import java.util.Comparator;

import com.github.phlank.musicplayer.model.Song;

public class SongNumberComparable implements Comparator<Song> {

	@Override
	public int compare(Song o1, Song o2) {
		return o1.getNumber().compareTo(o2.getNumber());
	}

}
