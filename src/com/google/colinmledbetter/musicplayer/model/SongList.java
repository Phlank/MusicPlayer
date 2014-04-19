package com.google.colinmledbetter.musicplayer.model;

public interface SongList extends Iterable<Song> {

	public void addSong(Song song);

	public void removeSong(Song song);

	public Song getCurrentSong();

	public Song previousSong();

	public Song nextSong();

	public int size();

}
