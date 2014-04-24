package com.github.phlank.musicplayer.model.comparable;

import java.util.Comparator;

import com.github.phlank.musicplayer.model.Song;

public class SongArtistTitleComparable implements Comparator<Song> {

	@Override
	public int compare(Song o1, Song o2) {
		return o1.getArtistTitle().compareTo(o2.getArtistTitle());
	}

}
