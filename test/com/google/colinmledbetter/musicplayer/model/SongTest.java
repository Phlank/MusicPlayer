package com.google.colinmledbetter.musicplayer.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
import com.google.colinmledbetter.musicplayer.model.exceptions.UnreadableSongException;

public class SongTest {
	
	private Song songWithTag;
	private Song songWithNoTag;
	
	@Before
	public void loadSong() throws UnreadableSongException, CorruptSongException {
		songWithTag = new Song("test-assets/Phlank_Document_1.mp3");
		songWithNoTag = new Song("test-assets/sine440.wav");
	}
	
	@Test
	public void testSongTitleForSongWithTag() {
		Assert.assertEquals("Document 1", songWithTag.getSongTitle());
	}
	
	@Test
	public void testArtistTitleForSongWithTag() {
		Assert.assertEquals("Phlank", songWithTag.getArtistTitle());
	}
	
	@Test
	public void testAlbumTitleForSongWithTag() {
		Assert.assertEquals("Unsigned Music", songWithTag.getAlbumTitle());
	}
	
	@Test
	public void testAlbumArtistTitleForSongWithTag() {
		Assert.assertEquals("Phlank", songWithTag.getAlbumArtistTitle());
	}
	
	@Test
	public void testSongTitleForSongWithNoTag() {
		Assert.assertEquals("Unknown Song", songWithNoTag.getSongTitle());
	}
	
	@Test
	public void testArtistTitleForSongWithNoTag() {
		Assert.assertEquals("Unknown Artist", songWithNoTag.getArtistTitle());
	}
	
	@Test
	public void testAlbumArtistTitleForSongWithNoTag() {
		Assert.assertEquals("Unknown Artist", songWithNoTag.getAlbumArtistTitle());
	}
	
	@Test
	public void testNumberOfSeconds() {
		Assert.assertEquals(302, songWithTag.getNumberOfSeconds());
	}

}
