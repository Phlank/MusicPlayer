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
		copy = new Song("test-assets/sine440tagged.mp3");
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
		copy.setSongTitle("Not the same");
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
		copy.setSongNumber("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentSongDiskNumber() {
		copy.setSongDiskNumber("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentYear() {
		copy.setSongYear("Not the same");
		Assert.assertFalse(song.equals(copy));
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
