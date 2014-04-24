package com.github.phlank.musicplayer.model;

import java.util.Comparator;

/**
 * @author Colin Ledbetter
 */
public interface SongList extends Iterable<Song> {

	public boolean addSong(Song song);

	public boolean removeSong(Song song);

	public Song getCurrentSong();
	
	public boolean setCurrentSong(Song song);

	public Song previousSong();

	public Song nextSong();
	
	public Song firstSong();

	public int size();

	public void sort(Comparator<Song> comparable);

}
