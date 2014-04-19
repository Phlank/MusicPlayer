package com.google.colinmledbetter.musicplayer.model;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.colinmledbetter.musicplayer.model.exceptions.UninteractableSongException;

public class FlacTaggedTest {

	private static final String TEST_SONG_FILE_PATH = "test-assets/sine440tagged.flac";

	private Song testSong;
	private BufferedImage artwork;

	@Before
	public void loadSong() throws Exception {
		testSong = new Song(TEST_SONG_FILE_PATH);
		artwork = ImageIO.read(new File("test-assets/artwork.png"));
	}

	@Test
	public void testSongTitle() {
		Assert.assertEquals("sine440", testSong.getSongTitle());
	}

	@Test
	public void testArtistTitle() {
		Assert.assertEquals("cledbetter", testSong.getArtistTitle());
	}

	@Test
	public void testAlbumTitle() {
		Assert.assertEquals("musicplayertests", testSong.getAlbumTitle());
	}

	@Test
	public void testAlbumArtistTitle() {
		Assert.assertEquals("cledbetter_album", testSong.getAlbumArtistTitle());
	}

	@Test
	public void testSongNumber() {
		Assert.assertEquals("02", testSong.getSongNumber());
	}

	@Test
	public void testSongDiskNumber() {
		Assert.assertEquals("1", testSong.getSongDiskNumber());
	}

	@Test
	public void testSongYear() {
		Assert.assertEquals("2014", testSong.getSongYear());
	}

	@Test
	public void testSongTimeInSeconds() {
		Assert.assertEquals(0, testSong.getSongTimeInSeconds());
	}

	@Test
	public void testSongFormat() {
		Assert.assertEquals(SongFormat.FLAC, testSong.getSongFormat());
	}
	
	@Test
	public void hasWritableAndReadableArtworkField()
			throws UninteractableSongException {
		testSong.writeArtwork(artwork);
		Assert.assertNotEquals(testSong.getArtwork(), null);
	}

}
