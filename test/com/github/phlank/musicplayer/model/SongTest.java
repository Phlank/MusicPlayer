package com.github.phlank.musicplayer.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.phlank.musicplayer.model.exceptions.UninteractableSongException;

public class SongTest {

	Song song;
	Song copy;
	Song nonexistentSong;

	@Before
	public void loadSong() throws UninteractableSongException, IOException {
		song = new Song("test-assets/sine440tagged.mp3");
		copy = new Song.Builder().setFilepath(song.getFilepath())//
				.setTitle(song.getTitle())//
				.setArtistTitle(song.getArtistTitle())//
				.setAlbumTitle(song.getAlbumTitle())//
				.setAlbumArtistTitle(song.getAlbumArtistTitle())//
				.setNumber(song.getNumber())//
				.setDiskNumber(song.getDiskNumber())//
				.setYear(song.getYear())//
				.setLength(song.getLength() + "")//
				.setHeaderFormat(song.getHeaderFormat())//
				.build();
		nonexistentSong = new Song.Builder()
				.setFilepath("thisfiledoesnotexist")//
				.setTitle(song.getTitle())//
				.setArtistTitle(song.getArtistTitle())//
				.setAlbumTitle(song.getAlbumTitle())//
				.setAlbumArtistTitle(song.getAlbumArtistTitle())//
				.setNumber(song.getNumber())//
				.setDiskNumber(song.getDiskNumber())//
				.setYear(song.getYear())//
				.setLength(song.getLength() + "")//
				.setHeaderFormat(song.getHeaderFormat())//
				.build();
	}

	@Test
	public void songEqualToSelf() {
		Assert.assertTrue(song.equals(song));
	}

	@Test
	public void songEqualToCopy() {
		Assert.assertTrue(song.equals(copy));
	}

	@Test
	public void songNotEqualToNull() {
		Assert.assertFalse(song.equals(null));
	}

	@Test
	public void songNotEqualToSongWithDifferentTitle()
			throws UninteractableSongException {
		copy.setTitle("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentArtistTitle()
			throws UninteractableSongException {
		copy.setArtistTitle("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentAlbumTitle() {
		copy.setAlbumTitle("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentAlbumArtistTitle() {
		copy.setAlbumArtistTitle("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentSongNumber() {
		copy.setNumber("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentSongDiskNumber() {
		copy.setDiskNumber("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToSongWithDifferentYear() {
		copy.setYear("Not the same");
		Assert.assertFalse(song.equals(copy));
	}

	@Test
	public void songNotEqualToObjectOfDifferentClass() {
		Assert.assertFalse(song.equals(""));
	}

	@Test
	public void testToStringReturnsTitle() {
		Assert.assertEquals("sine440", song.toString());
	}

	@Test
	public void testHashCode() {
		Assert.assertEquals(2088270192, song.hashCode());
	}

	@Test
	public void testFileUpdatedOnWriteFields()
			throws UninteractableSongException, InterruptedException {
		String[] startFields = getFields(song);
		changeSongFields(song);
		String[] expectedFields = getFields(song);
		song.writeFields();
		Song newsong = new Song(song.getFilepath());
		String[] actualFields = getFields(newsong);
		for (int i = 0; i < expectedFields.length; i++) {
			Assert.assertEquals(expectedFields[i], actualFields[i]);
		}
		resetOldFields(song, startFields);
	}

	private static String[] getFields(Song song) {
		String[] returnable = { song.getTitle(), song.getArtistTitle(),
				song.getAlbumTitle(), song.getAlbumArtistTitle(),
				song.getNumber(), song.getDiskNumber(), song.getYear() };
		return returnable;
	}

	private static void changeSongFields(Song song) {
		song.setTitle("9999");
		song.setArtistTitle("9999");
		song.setAlbumTitle("9999");
		song.setAlbumArtistTitle("9999");
		song.setNumber("99");
		song.setDiskNumber("9");
		song.setYear("9999");
	}

	private static void resetOldFields(Song song, String[] fields)
			throws UninteractableSongException, InterruptedException {
		song.setTitle(fields[0]);
		song.setArtistTitle(fields[1]);
		song.setAlbumTitle(fields[2]);
		song.setAlbumArtistTitle(fields[3]);
		song.setNumber(fields[4]);
		song.setDiskNumber(fields[5]);
		song.setYear(fields[6]);
		song.writeFields();
	}

	@Test(expected = UninteractableSongException.class)
	public void loadWavTaggedThrowsUninteractableSongException()
			throws UninteractableSongException {
		new Song("test-assets/sine440tagged_uninteractable.wav");
	}

	@Test(expected = UninteractableSongException.class)
	public void loadNonSongFileThrowsUninteractableSongException()
			throws UninteractableSongException {
		new Song("test-assets/notasong.txt");
	}

	@Test(expected = UninteractableSongException.class)
	public void loadSongThatDoesNotExistThrowsException()
			throws UninteractableSongException {
		new Song("thisfiledoesnotexist");
	}

	@Test(expected = UninteractableSongException.class)
	public void testWriteTagsOnNonexistantFileThrowsException()
			throws UninteractableSongException {
		nonexistentSong.writeFields();
	}

	@Test(expected = UninteractableSongException.class)
	public void testWriteArtworkOnNonexistantFileThrowsException()
			throws UninteractableSongException, IOException {
		BufferedImage image = ImageIO.read(new File("test-assets/artwork.png"));
		nonexistentSong.writeArtwork(image);
	}

}
