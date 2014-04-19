package com.google.colinmledbetter.musicplayer.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.colinmledbetter.musicplayer.model.exceptions.UninteractableSongException;
import com.google.common.collect.Lists;

public class Album implements SongList {

	private ArrayList<Song> songs;
	private int currentIndex;

	private String title;
	private String artistTitle;
	private BufferedImage artwork;

	public Album(Song song) throws UninteractableSongException {
		songs = Lists.newArrayList();
		currentIndex = 0;
		addSong(song);
		title = song.getAlbumTitle();
		artistTitle = song.getAlbumArtistTitle();
		artwork = song.getArtwork();
	}

	@Override
	public Iterator<Song> iterator() {
		return songs.iterator();
	}

	@Override
	public void addSong(Song song) {
		songs.add(song);
	}

	@Override
	public void removeSong(Song song) {
		songs.remove(song);
		songs.trimToSize();
		if (currentIndex == songs.size()) {
			currentIndex--;
		}
	}

	@Override
	public Song getCurrentSong() {
		if (songs.size() > 0) {
			return songs.get(currentIndex);
		} else {
			return null;
		}
	}

	@Override
	public Song nextSong() {
		currentIndex++;
		if (currentIndex < songs.size()) {
			return songs.get(currentIndex);
		} else {
			currentIndex--;
			return null;
		}
	}

	@Override
	public Song previousSong() {
		currentIndex--;
		if (currentIndex >= 0 && !songs.isEmpty()) {
			return songs.get(currentIndex);
		} else {
			currentIndex++;
			return null;
		}
	}

	@Override
	public int size() {
		return songs.size();
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artistTitle;
	}
	
	public BufferedImage getArtwork() {
		return artwork;
	}

}
