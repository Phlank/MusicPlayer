package com.google.colinmledbetter.musicplayer.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
import com.google.colinmledbetter.musicplayer.model.exceptions.UninteractableSongException;

public class AlbumTest {

	Album album;
	Song firstSong;
	Song lastSong;

	@Before
	public void loadSongsIntoAlbum() throws UninteractableSongException,
			CorruptSongException {
		firstSong = new Song("test-assets/sine440tagged.mp3");
		lastSong = new Song("test-assets/sine440tagged.flac");
		album = new Album(firstSong);
		album.addSong(lastSong);
	}

	@Test
	public void testOnInitializeTitleIsLoaded() {
		Assert.assertEquals("musicplayertests", album.getTitle());
	}

	@Test
	public void testOnInitializeArtistIsLoaded() {
		Assert.assertEquals("cledbetter_album", album.getArtist());
	}

	@Test
	public void testOnInitializeCurrentSongIsFirstSong() {
		Assert.assertEquals(album.getCurrentSong(), firstSong);
	}

	@Test
	public void testNextReturnsNextSong() {
		Assert.assertEquals(lastSong, album.nextSong());
	}

	@Test
	public void testNextAtEndOfAlbumReturnsNull() {
		album.nextSong();
		Assert.assertEquals(null, album.nextSong());
	}

	@Test
	public void testPreviousReturnsPreviousSongIfNotOnFirstIndex() {
		album.nextSong();
		Song actual = album.previousSong();
		Assert.assertEquals(firstSong, actual);
	}

	@Test
	public void testPreviousAtStartOfAlbumReturnsNull() {
		Assert.assertEquals(null, album.previousSong());
	}

	@Test
	public void testOnFirstSongRemoveThatSecondSongBecomesFirstSong() {
		album.removeSong(firstSong);
		Assert.assertEquals(lastSong, album.getCurrentSong());
	}

	@Test
	public void testOnLastSongRemoveThatIndexMovesBack() {
		album.nextSong();
		album.removeSong(lastSong);
		Assert.assertEquals(firstSong, album.getCurrentSong());
	}

}
