package com.google.colinmledbetter.musicplayer.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WavUntaggedTest {

	private static final String TEST_SONG_FILE_PATH =
			"test-assets/sine440untagged.wav";

	private Song testSong;

	@Before
	public void loadSong() throws Exception {
		testSong = new Song(TEST_SONG_FILE_PATH);
	}

	@Test
	public void testSongTitle() {
		Assert.assertEquals("Unknown Song", testSong.getSongTitle());
	}

	@Test
	public void testArtistTitle() {
		Assert.assertEquals("Unknown Artist", testSong.getArtistTitle());
	}

	@Test
	public void testAlbumTitle() {
		Assert.assertEquals("Unknown Album", testSong.getAlbumTitle());
	}

	@Test
	public void testAlbumArtistTitle() {
		Assert.assertEquals("Unknown Artist", testSong.getAlbumArtistTitle());
	}

	@Test
	public void testSongNumber() {
		Assert.assertEquals("", testSong.getSongNumber());
	}

	@Test
	public void testSongDiskNumber() {
		Assert.assertEquals("", testSong.getSongDiskNumber());
	}

	@Test
	public void testSongYear() {
		Assert.assertEquals("Unknown Year", testSong.getSongYear());
	}

	@Test
	public void testSongTimeInSeconds() {
		Assert.assertEquals(0, testSong.getSongTimeInSeconds());
	}

	@Test
	public void testSongFormat() {
		Assert.assertEquals(SongFormat.WAV, testSong.getSongFormat());
	}

}
