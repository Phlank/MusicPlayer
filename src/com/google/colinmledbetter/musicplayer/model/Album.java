package com.google.colinmledbetter.musicplayer.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import com.google.colinmledbetter.musicplayer.model.comparable.SongNumberComparable;
import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
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
		songs.add(song);
		loadAlbumInfo(song);
	}

	private void loadAlbumInfo(Song song) {
		title = song.getAlbumTitle();
		artistTitle = song.getAlbumArtistTitle();
		loadArtwork(song);
	}

	private void loadArtwork(Song song) {
		try {
			artwork = song.getArtwork();
		} catch (UninteractableSongException | CorruptSongException e) {
			artwork = null;
		}
	}

	@Override
	public Iterator<Song> iterator() {
		return songs.iterator();
	}

	@Override
	public boolean addSong(Song song) {
		if (songInfoMatchesAlbumInfo(song) && !songs.contains(song)) {
			songs.add(song);
			sort(new SongNumberComparable());
			return true;
		} else {
			return false;
		}
	}

	private boolean songInfoMatchesAlbumInfo(Song song) {
		return song.getAlbumTitle().equals(title)
				&& song.getAlbumArtistTitle().equals(artistTitle);
	}

	@Override
	public boolean removeSong(Song song) {
		boolean success = songs.remove(song);
		songs.trimToSize();
		if (currentIndex == songs.size()) {
			currentIndex--;
		}
		return success;
	}

	@Override
	public Song getCurrentSong() {
		if (!songs.isEmpty()) {
			return songs.get(currentIndex);
		} else {
			return null;
		}
	}

	@Override
	public Song nextSong() {
		currentIndex++;
		if (currentIndex < size()) {
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
	
	@Override
	public void sort(Comparator<Song> comparable) {
		songs.sort(comparable);
	}

	public String getTitle() {
		return title;
	}

	public String getArtistTitle() {
		return artistTitle;
	}

	public BufferedImage getArtwork() {
		return artwork;
	}

}
