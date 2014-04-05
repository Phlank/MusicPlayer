package com.google.colinmledbetter.musicplayer.model;

import org.junit.Assert;
import org.junit.Test;

public class SongTest {
	
	private Song song;
	
	public void testSongTitle() {
		Assert.assertEquals("Document 1", song.getTitle());
	}

}
