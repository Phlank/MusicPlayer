package com.github.phlank.musicplayer.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.phlank.musicplayer.model.Song;
import com.github.phlank.musicplayer.model.SongFormat;
import com.github.phlank.musicplayer.model.exceptions.CorruptSongException;
import com.github.phlank.musicplayer.model.exceptions.UninteractableSongException;

public class Mp3TaggedTest {

	private static final String TEST_SONG_FILE_PATH = "test-assets/sine440tagged.mp3";

	private Song testSong;
	private BufferedImage artwork;

	@Before
	public void loadSong() throws Exception {
		testSong = new Song(TEST_SONG_FILE_PATH);
		artwork = ImageIO.read(new File("test-assets/artwork.png"));
	}

	@Test
	public void testSongTitle() {
		Assert.assertEquals("sine440", testSong.getTitle());
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
		Assert.assertEquals("01", testSong.getNumber());
	}

	@Test
	public void testSongDiskNumber() {
		Assert.assertEquals("1", testSong.getDiskNumber());
	}

	@Test
	public void testSongYear() {
		Assert.assertEquals("2014", testSong.getYear());
	}

	@Test
	public void testSongTimeInSeconds() {
		Assert.assertEquals(0, testSong.getLength());
	}

	@Test
	public void testSongFormat() {
		Assert.assertEquals(SongFormat.MP3, testSong.getFormat());
	}

	@Test
	public void hasWritableAndReadableArtworkField()
			throws UninteractableSongException, IOException,
			CorruptSongException {
		testSong.writeArtwork(artwork);
		Assert.assertNotEquals(testSong.getArtwork(), null);
	}

}
