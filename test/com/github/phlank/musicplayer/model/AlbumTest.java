package com.github.phlank.musicplayer.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.phlank.musicplayer.model.exceptions.UninteractableSongException;

public class AlbumTest {

	Album album;
	Song firstSong;
	Song lastSong;

	@Before
	public void loadSongsIntoAlbum() throws UninteractableSongException {
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
		Assert.assertEquals("cledbetter_album", album.getArtistTitle());
	}

	@Test
	public void testOnInitializeCurrentSongIsFirstSong() {
		Assert.assertEquals(firstSong, album.getCurrentSong());
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

	@Test
	public void testReturnsFalseIfTryingToAddSongThatDoesNotMatchAlbumInfo()
			throws UninteractableSongException {
		Song song = new Song("test-assets/sine440untagged.mp3");
		Assert.assertFalse(album.addSong(song));
	}

	@Test
	public void testReturnsFalseIfTryingToAddSongThatIsAlreadyInAlbum() {
		Assert.assertFalse(album.addSong(firstSong));
	}

	@Test
	public void testIsIterable() {
		boolean firstSongFound = false;
		boolean lastSongFound = false;
		for (Song song : album) {
			if (song.equals(firstSong)) {
				firstSongFound = true;
			} else if (song.equals(lastSong)) {
				lastSongFound = true;
			}
		}
		Assert.assertTrue(firstSongFound && lastSongFound);
	}

	@Test
	public void testLoadsArtwork() {
		Assert.assertTrue(album.getArtwork() != null);
	}

	@Test
	public void testArtworkFromBadSongIsNull()
			throws UninteractableSongException {
		Song badSong = new Song.Builder().setFilepath("thisfiledoesnotexist")//
				.setTitle("")//
				.setArtistTitle("")//
				.setAlbumTitle("")//
				.setAlbumArtistTitle("")//
				.setNumber("")//
				.setDiskNumber("")//
				.setYear("")//
				.setHeaderFormat("")//
				.setLength("0")//
				.build();
		Album album = new Album(badSong);
		Assert.assertNull(album.getArtwork());
	}

	@Test
	public void testCurrentSongOfEmptyAlbumIsNull() {
		album.removeSong(firstSong);
		album.removeSong(lastSong);
		Assert.assertNull(album.getCurrentSong());
	}

	@Test
	public void testAddSongAlreadyInAlbumReturnsFalse() {
		Assert.assertFalse(album.addSong(firstSong));
	}

	@Test
	public void testAddSongNotMatchingInfoReturnsFalse()
			throws UninteractableSongException {
		Assert.assertFalse(album.addSong(new Song(
				"test-assets/sine440untagged.mp3")));
	}

	@Test
	public void testSetCurrentSongWithSongInAlbumReturnsTrue() {
		Assert.assertTrue(album.setCurrentSong(lastSong));
	}

	@Test
	public void testSetCurrentSongWidthSongNotInAlbumReturnsFalse()
			throws UninteractableSongException {
		Assert.assertFalse(album.setCurrentSong(new Song(
				"test-assets/sine440untagged.mp3")));
	}

	@Test
	public void testSetCurrentSongChangesCurrentSong() {
		album.setCurrentSong(lastSong);
		Assert.assertEquals(lastSong, album.getCurrentSong());
	}
	
	@Test
	public void testFirstSongReturnsFirstSong() {
		album.nextSong();
		Assert.assertEquals(firstSong, album.firstSong());
	}
	
	@Test
	public void testOnFirstSongCurrentSongBecomesFirstSong() {
		album.nextSong();
		album.firstSong();
		Assert.assertEquals(firstSong, album.getCurrentSong());
	}
	
	@Test
	public void testFirstSongReturnsNullWhenEmpty() {
		album.removeSong(firstSong);
		album.removeSong(lastSong);
		Assert.assertNull(album.firstSong());
	}

}
