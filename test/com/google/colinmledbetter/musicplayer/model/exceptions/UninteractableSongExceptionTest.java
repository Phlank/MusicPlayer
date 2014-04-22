package com.google.colinmledbetter.musicplayer.model.exceptions;

import org.junit.Test;

import com.google.colinmledbetter.musicplayer.model.Song;

public class UninteractableSongExceptionTest {

	@Test(expected = UninteractableSongException.class)
	public void loadWavTaggedThrowsUninteractableSongException()
			throws UninteractableSongException,
			CorruptSongException {
		new Song("test-assets/sine440tagged_uninteractable.wav");
	}

	@Test(expected = UninteractableSongException.class)
	public void loadNonSongFileThrowsUninteractableSongException()
			throws UninteractableSongException,
			CorruptSongException {
		new Song("test-assets/notasong.txt");
	}

	@Test(expected = UninteractableSongException.class)
	public void loadSongThatDoesNotExistThrowsException()
			throws UninteractableSongException,
			CorruptSongException {
		new Song("thisfiledoesnotexist");
	}

	@Test(expected = UninteractableSongException.class)
	public void getSongArtworkOnSongWithNowArtworkThrowsException()
			throws UninteractableSongException,
			CorruptSongException {
		Song song = new Song("test-assets/sine440untagged.mp3");
		song.getArtwork();
	}

}
