package com.google.colinmledbetter.musicplayer.model.comparable;

import java.util.Comparator;

import com.google.colinmledbetter.musicplayer.model.Song;

public class SongAlbumTitleComparable implements Comparator<Song> {

	@Override
	public int compare(Song o1, Song o2) {
		return o1.getAlbumTitle().compareTo(o2.getAlbumTitle());
	}

}
