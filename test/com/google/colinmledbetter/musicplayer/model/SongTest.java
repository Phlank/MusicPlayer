package com.google.colinmledbetter.musicplayer.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
import com.google.colinmledbetter.musicplayer.model.exceptions.UnreadableSongException;

public class SongTest {
	
	private Song mp3Tagged;
	private Song mp3Untagged;
	private Song wavTagged;
	private Song wavUntagged;
	
	@Before
	public void loadSong() throws UnreadableSongException, CorruptSongException {
		mp3Tagged = new Song("test-assets/sine440tagged.mp3");
		mp3Untagged = new Song("test-assets/sine440untagged.mp3");
		wavTagged = new Song("test-assets/sine440tagged.wav");
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
	public void testNumberOfSeconds() {
		Assert.assertEquals(0, mp3Tagged.getNumberOfSeconds());
	}

}
