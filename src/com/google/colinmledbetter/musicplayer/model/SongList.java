package com.google.colinmledbetter.musicplayer.model;

import java.util.Comparator;

public interface SongList extends Iterable<Song> {

	public boolean addSong(Song song);

	public boolean removeSong(Song song);

	public Song getCurrentSong();

	public Song previousSong();

	public Song nextSong();

	public int size();

	public void sort(Comparator<Song> comparable);

}
