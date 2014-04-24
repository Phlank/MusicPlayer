package com.github.phlank.musicplayer.model.exceptions;

import org.junit.Test;

import com.github.phlank.musicplayer.model.Song;
import com.github.phlank.musicplayer.model.exceptions.CorruptSongException;
import com.github.phlank.musicplayer.model.exceptions.UninteractableSongException;

public class UninteractableSongExceptionTest {

	@Test(expected = UninteractableSongException.class)
	public void loadWavTaggedThrowsUninteractableSongException()
			throws UninteractableSongException, CorruptSongException {
		new Song("test-assets/sine440tagged_uninteractable.wav");
	}

	@Test(expected = UninteractableSongException.class)
	public void loadNonSongFileThrowsUninteractableSongException()
			throws UninteractableSongException, CorruptSongException {
		new Song("test-assets/notasong.txt");
	}

	@Test(expected = UninteractableSongException.class)
	public void loadSongThatDoesNotExistThrowsException()
			throws UninteractableSongException, CorruptSongException {
		new Song("thisfiledoesnotexist");
	}

}
