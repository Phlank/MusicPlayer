package com.google.colinmledbetter.musicplayer.model;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
import com.google.colinmledbetter.musicplayer.model.exceptions.UninteractableSongException;

public class SongTest {

	Song song;
	Song copy;

	@Before
	public void loadSong() throws UninteractableSongException,
			CorruptSongException, IOException {
		song = new Song("test-assets/sine440tagged.mp3");
		copy = new Song.Builder().setFilepath(song.getFilepath())//
				.setTitle(song.getTitle())//
				.setArtistTitle(song.getArtistTitle())//
				.setAlbumTitle(song.getAlbumTitle())//
				.setAlbumArtistTitle(song.getAlbumArtistTitle())//
				.setNumber(song.getNumber())//
				.setDiskNumber(song.getDiskNumber())//
				.setYear(song.getYear())//
				.setLength(song.getLength()+"")//
				.setHeaderFormat(song.getHeaderFormat())//
				.build();
	}

	@Test
	public void songEqualToSelf() {
		Assert.assertTrue(song.equals(song));
	}

	@Test
	public void songEqualToCopy() {
		Assert.assertTrue(song.equals(copy));
	}

	@Test
	public void songNotEqualToNull() {
		Assert.assertFalse(song.equals(null));
	}

	@Test
	public void songNotEqualToSongWithDifferentTitle() {
		copy.setTitle("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentArtistTitle() {
		copy.setArtistTitle("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentAlbumTitle() {
		copy.setAlbumTitle("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentAlbumArtistTitle() {
		copy.setAlbumArtistTitle("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentSongNumber() {
		copy.setNumber("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentSongDiskNumber() {
		copy.setDiskNumber("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentYear() {
		copy.setYear("Not the same");
		Assert.assertFalse(song.equals(copy));
	}
	
	@Test
	public void songNotEqualToObjectOfDifferentClass() {
		Assert.assertFalse(song.equals(""));
	}

	@Test
	public void testToStringReturnsTitle() {
		Assert.assertEquals("sine440", song.toString());
	}

	@Test
	public void testHashCode() {
		Assert.assertEquals(2088270192, song.hashCode());
	}

}
