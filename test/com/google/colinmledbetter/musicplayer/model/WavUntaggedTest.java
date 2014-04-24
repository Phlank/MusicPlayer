package com.google.colinmledbetter.musicplayer.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
import com.google.colinmledbetter.musicplayer.model.exceptions.UninteractableSongException;

public class WavUntaggedTest {

	private static final String TEST_SONG_FILE_PATH = "test-assets/sine440untagged.wav";

	private Song testSong;
	private BufferedImage artwork;

	@Before
	public void loadSong() throws Exception {
		testSong = new Song(TEST_SONG_FILE_PATH);
		artwork = ImageIO.read(new File("test-assets/artwork.png"));
	}

	@Test
	public void testSongTitle() {
		Assert.assertEquals("Unknown Song", testSong.getTitle());
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
		Assert.assertEquals("", testSong.getNumber());
	}

	@Test
	public void testSongDiskNumber() {
		Assert.assertEquals("", testSong.getDiskNumber());
	}

	@Test
	public void testSongYear() {
		Assert.assertEquals("Unknown Year", testSong.getYear());
	}

	@Test
	public void testSongTimeInSeconds() {
		Assert.assertEquals(0, testSong.getLength());
	}

	@Test
	public void testSongFormat() {
		Assert.assertEquals(SongFormat.WAV, testSong.getFormat());
	}

	@Test(expected = UninteractableSongException.class)
	public void testHasUnwritableArtwork() throws UninteractableSongException,
			IOException, CorruptSongException {
		testSong.writeArtwork(artwork);
	}

	@Test
	public void testGetArtworkReturnsNull() throws UninteractableSongException,
			CorruptSongException {
		Assert.assertTrue(testSong.getArtwork() == null);
	}

}
