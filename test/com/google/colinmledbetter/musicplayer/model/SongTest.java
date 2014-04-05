package com.google.colinmledbetter.musicplayer.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class SongTest {
	
	private Song song;
	
	@Before
	public void loadSong() throws UnreadableSongException {
		song = new Song("test-assets/Phlank_Document_1.mp3");
	}
	
	@Test
	public void testSongTitle() {
		Assert.assertEquals("Document 1", song.getSongTitle());
	}
	
	@Test
	public void testArtistTitle() {
		Assert.assertEquals("Phlank", song.getArtistTitle());
	}
	
	@Test
	public void testAlbumTitle() {
		Assert.assertEquals("Unsigned Music", song.getAlbumTitle());
	}
	
	@Test
	public void testAlbumArtistTitle() {
		Assert.assertEquals("Phlank", song.getAlbumArtistTitle());
	}

}
