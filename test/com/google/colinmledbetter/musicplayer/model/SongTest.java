package com.google.colinmledbetter.musicplayer.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
import com.google.colinmledbetter.musicplayer.model.exceptions.UninteractableSongException;

public class SongTest {
	
	private Song mp3Tagged;
	private Song mp3Untagged;
	private Song wavUntagged;
	
	@Before
	public void loadSong() throws UninteractableSongException, CorruptSongException {
		mp3Tagged = new Song("test-assets/sine440tagged.mp3");
		mp3Untagged = new Song("test-assets/sine440untagged.mp3");
		wavUntagged = new Song("test-assets/sine440untagged.wav");
	}
	
	@Test
	public void testSongTitleForMp3Tagged() {
		Assert.assertEquals("sine440", mp3Tagged.getSongTitle());
	}
	
	@Test
	public void testArtistTitleForMp3Tagged() {
		Assert.assertEquals("cledbetter", mp3Tagged.getArtistTitle());
	}
	
	@Test
	public void testAlbumTitleForMp3Tagged() {
		Assert.assertEquals("musicplayertests", mp3Tagged.getAlbumTitle());
	}
	
	@Test
	public void testAlbumArtistTitleForMp3Tagged() {
		Assert.assertEquals("cledbetter", mp3Tagged.getAlbumArtistTitle());
	}
	
	@Test
	public void testSongNumberForMp3Tagged() {
		Assert.assertEquals("1", mp3Tagged.getSongNumber());
	}
	
	@Test
	public void testSongDiskNumberForMp3Tagged() {
		Assert.assertEquals("", mp3Tagged.getSongDiskNumber());
	}
	
	@Test
	public void testSongYearForMp3Tagged() {
		Assert.assertEquals("2014", mp3Tagged.getSongYear());
	}
	
	@Test
	public void testSongTitleForMp3Untagged() {
		Assert.assertEquals("Unknown Song", mp3Untagged.getSongTitle());
	}
	
	@Test
	public void testArtistTitleForMp3Untagged() {
		Assert.assertEquals("Unknown Artist", mp3Untagged.getArtistTitle());
	}
	
	@Test
	public void testAlbumTitleForMp3Untagged() {
		Assert.assertEquals("Unknown Album", mp3Untagged.getAlbumTitle());
	}
	
	@Test
	public void testAlbumArtistTitleForMp3Untagged() {
		Assert.assertEquals("Unknown Artist", mp3Untagged.getAlbumArtistTitle());
	}
	
	@Test
	public void testSongNumberForMp3Untagged() {
		Assert.assertEquals("", mp3Untagged.getSongNumber());
	}
	
	@Test
	public void testSongDiskNumberForMp3Untagged() {
		Assert.assertEquals("", mp3Untagged.getSongDiskNumber());
	}
	
	@Test
	public void testSongYearForMp3Untagged() {
		Assert.assertEquals("Unknown Year", mp3Untagged.getSongYear());
	}
	
	@Test
	public void testSongTitleForWavUntagged() {
		Assert.assertEquals("Unknown Song", wavUntagged.getSongTitle());
	}
	
	@Test
	public void testArtistTitleForWavUntagged() {
		Assert.assertEquals("Unknown Artist", wavUntagged.getArtistTitle());
	}
	
	@Test
	public void testAlbumTitleForWavUntagged() {
		Assert.assertEquals("Unknown Album", wavUntagged.getAlbumTitle());
	}
	
	@Test
	public void testAlbumArtistTitleForWavUntagged() {
		Assert.assertEquals("Unknown Artist", wavUntagged.getAlbumArtistTitle());
	}
	
	@Test
	public void testNumberOfSeconds() {
		Assert.assertEquals(0, mp3Tagged.getSongTimeInSeconds());
	}
	
	@Test(expected = UninteractableSongException.class)
	public void loadWavTaggedThrowsUnreadableSongException() throws UninteractableSongException, CorruptSongException {
		new Song("test-assets/sine440tagged.wav");
	}
	
	@Test(expected = UninteractableSongException.class)
	public void loadNonSongFile() throws UninteractableSongException, CorruptSongException {
		new Song("test-assets/notasong.txt");
	}

}
