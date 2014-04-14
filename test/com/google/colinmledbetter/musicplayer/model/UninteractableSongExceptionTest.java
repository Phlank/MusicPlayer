package com.google.colinmledbetter.musicplayer.model;

import org.junit.Test;

import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
import com.google.colinmledbetter.musicplayer.model.exceptions.UninteractableSongException;

public class UninteractableSongExceptionTest {

	@Test(expected = UninteractableSongException.class)
	public void loadWavTaggedThrowsUninteractableSongException()
			throws UninteractableSongException, CorruptSongException {
		new Song("test-assets/sine440tagged.wav");
	}

	@Test(expected = UninteractableSongException.class)
	public void loadNonSongFileThrowsUninteractableSongException()
			throws UninteractableSongException, CorruptSongException {
		new Song("test-assets/notasong.txt");
	}

}
