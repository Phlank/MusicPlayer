package com.github.phlank.musicplayer.model.comparable;

import java.util.Comparator;

import com.github.phlank.musicplayer.model.Song;

public class SongLengthComparable implements Comparator<Song> {

	@Override
	public int compare(Song o1, Song o2) {
		return new Integer(o1.getLength())
				.compareTo(new Integer(o2.getLength()));
	}

}
