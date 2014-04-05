package com.google.colinmledbetter.musicplayer.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class SongTest {
	
	private Song song;
	
	@Before
	public void loadSong() {
		song = new Song("test-assets/Phlank_Document_1.mp3");
	}
	
	@Test
	public void testSongTitle() {
		Assert.assertEquals("Document 1", song.getSongTitle());
	}

}
