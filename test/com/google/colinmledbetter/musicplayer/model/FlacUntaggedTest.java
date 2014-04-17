package com.google.colinmledbetter.musicplayer.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FlacUntaggedTest {

	private static final String TEST_SONG_FILE_PATH = "test-assets/sine440untagged.flac";

	private Song testSong;

	@Before
	public void loadSong() throws Exception {
		testSong = new Song(TEST_SONG_FILE_PATH);
	}

	@Test
	public void testSongTitleForMp3Untagged() {
		Assert.assertEquals("Unknown Song", testSong.getSongTitle());
	}

	@Test
	public void testArtistTitleForMp3Untagged() {
		Assert.assertEquals("Unknown Artist", testSong.getArtistTitle());
	}

	@Test
	public void testAlbumTitleForMp3Untagged() {
		Assert.assertEquals("Unknown Album", testSong.getAlbumTitle());
	}

	@Test
	public void testAlbumArtistTitleForMp3Untagged() {
		Assert.assertEquals("Unknown Artist", testSong.getAlbumArtistTitle());
	}

	@Test
	public void testSongNumberForMp3Untagged() {
		Assert.assertEquals("", testSong.getSongNumber());
	}

	@Test
	public void testSongDiskNumberForMp3Untagged() {
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
		Assert.assertEquals(SongFormat.FLAC, testSong.getSongFormat());
	}

}
