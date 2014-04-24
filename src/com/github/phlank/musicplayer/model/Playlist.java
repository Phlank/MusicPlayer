package com.github.phlank.musicplayer.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Colin Ledbetter
 */
public class Playlist implements SongList {

	private List<Song> songs;
	private HashMap<Integer, Song> songsInOrder;

	public Playlist() {
		songs = Lists.newArrayList();
		songsInOrder = Maps.newHashMap();
	}

	@Override
	public Iterator<Song> iterator() {
		return songs.iterator();
	}

	@Override
	public boolean addSong(Song song) {
		songs.add(song);
		songsInOrder.put(songsInOrder.size(), song);
		return true;
	}

	@Override
	public boolean removeSong(Song song) {
		// TODO implement
		return true;
	}

	@Override
	public Song getCurrentSong() {
		// TODO implement
		return null;
	}

	@Override
	public Song previousSong() {
		// TODO implement
		return null;
	}

	@Override
	public Song nextSong() {
		// TODO implement
		return null;
	}

	@Override
	public int size() {
		// TODO implement
		return 0;
	}

	@Override
	public void sort(Comparator<Song> comparable) {
		// TODO implement

	}

}
