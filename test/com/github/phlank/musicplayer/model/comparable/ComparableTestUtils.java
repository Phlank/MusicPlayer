package com.github.phlank.musicplayer.model.comparable;

import java.util.List;

import com.github.phlank.musicplayer.model.Song;
import com.google.common.collect.Lists;

public class ComparableTestUtils {

	/*
	 * A list of songs with comparable orders that came about by scrambling numbers
	 * 
	 * Filepath order:		[5, 2, 1, 6, 9, 4, 3, 8, 7, 0]
	 * Title order:			[5, 9, 2, 1, 3, 6, 4, 0, 7, 8]
	 * Artist order:		[9, 7, 5, 0, 1, 6, 3, 8, 2, 4]
	 * Album order:			[3, 2, 6, 1, 4, 8, 5, 7, 9, 0]
	 * Album artist order:	[9, 0, 4, 5, 1, 8, 3, 2, 7, 6]
	 * Number order:		[5, 0, 3, 7, 8, 4, 9, 1, 6, 2]
	 * Disk number order:	[1, 9, 3, 8, 7, 5, 0, 4, 6, 2]
	 * Year order:			[9, 1, 4, 8, 5, 0, 2, 7, 3, 6]
	 * Length order:		[3, 1, 7, 6, 5, 8, 4, 2, 0, 9]
	 * 
	 * In case we ever need more, here are some more:
	 * 
	 * [2, 8, 6, 9, 5, 4, 0, 1, 3, 7]
	 * [6, 0, 9, 3, 2, 7, 1, 5, 4, 8]
	 * [2, 0, 7, 9, 1, 6, 5, 3, 4, 8]
	 * [4, 6, 8, 2, 3, 1, 0, 7, 5, 9]
	 * [3, 0, 7, 9, 1, 6, 2, 8, 5, 4]
	 * [5, 6, 0, 1, 3, 9, 4, 7, 8, 2]
	 * [7, 9, 3, 6, 5, 2, 1, 8, 0, 4]
	 * [6, 7, 1, 2, 9, 4, 8, 0, 5, 3]
	 * [0, 6, 4, 3, 1, 8, 5, 7, 2, 9]
	 * [7, 8, 3, 6, 2, 9, 5, 0, 4, 1]
	 * [8, 9, 6, 1, 0, 4, 5, 2, 7, 3]
	 */
	public static final List<Song> sortableSongs = //
	Lists.newArrayList(//
			new Song.Builder().setFilepath("9")//
					.setTitle("7")//
					.setArtistTitle("3")//
					.setAlbumTitle("9")//
					.setAlbumArtistTitle("1")//
					.setNumber("1")//
					.setDiskNumber("6")//
					.setYear("0005")//
					.setLength("8")//
					.setHeaderFormat("")//
					.build(),//
			new Song.Builder().setFilepath("2")//
					.setTitle("3")//
					.setArtistTitle("4")//
					.setAlbumTitle("3")//
					.setAlbumArtistTitle("4")//
					.setNumber("7")//
					.setDiskNumber("0")//
					.setYear("0001")//
					.setLength("1")//
					.setHeaderFormat("")//
					.build(),//
			new Song.Builder().setFilepath("1")//
					.setTitle("2")//
					.setArtistTitle("8")//
					.setAlbumTitle("1")//
					.setAlbumArtistTitle("7")//
					.setNumber("9")//
					.setDiskNumber("9")//
					.setYear("0006")//
					.setHeaderFormat("")//
					.setLength("7")//
					.build(),//
			new Song.Builder().setFilepath("6")//
					.setTitle("4")//
					.setArtistTitle("6")//
					.setAlbumTitle("0")//
					.setAlbumArtistTitle("6")//
					.setNumber("2")//
					.setDiskNumber("2")//
					.setYear("0008")//
					.setHeaderFormat("")//
					.setLength("0")//
					.build(),//
			new Song.Builder().setFilepath("5")//
					.setTitle("6")//
					.setArtistTitle("9")//
					.setAlbumTitle("4")//
					.setAlbumArtistTitle("2")//
					.setNumber("5")//
					.setDiskNumber("7")//
					.setYear("0002")//
					.setHeaderFormat("")//
					.setLength("6")//
					.build(),//
			new Song.Builder().setFilepath("0")//
					.setTitle("0")//
					.setArtistTitle("2")//
					.setAlbumTitle("6")//
					.setAlbumArtistTitle("3")//
					.setNumber("0")//
					.setDiskNumber("5")//
					.setYear("0004")//
					.setHeaderFormat("")//
					.setLength("4")//
					.build(),//
			new Song.Builder().setFilepath("3")//
					.setTitle("5")//
					.setArtistTitle("5")//
					.setAlbumTitle("2")//
					.setAlbumArtistTitle("9")//
					.setNumber("8")//
					.setDiskNumber("8")//
					.setYear("0009")//
					.setHeaderFormat("")//
					.setLength("3")//
					.build(),//
			new Song.Builder().setFilepath("8")//
					.setTitle("8")//
					.setArtistTitle("1")//
					.setAlbumTitle("7")//
					.setAlbumArtistTitle("8")//
					.setNumber("3")//
					.setDiskNumber("4")//
					.setYear("0007")//
					.setHeaderFormat("")//
					.setLength("2")//
					.build(),//
			new Song.Builder().setFilepath("7")//
					.setTitle("9")//
					.setArtistTitle("7")//
					.setAlbumTitle("5")//
					.setAlbumArtistTitle("5")//
					.setNumber("4")//
					.setDiskNumber("3")//
					.setYear("0003")//
					.setHeaderFormat("")//
					.setLength("5")//
					.build(),//
			new Song.Builder().setFilepath("4")//
					.setTitle("1")//
					.setArtistTitle("0")//
					.setAlbumTitle("8")//
					.setAlbumArtistTitle("0")//
					.setNumber("6")//
					.setDiskNumber("1")//
					.setYear("0000")//
					.setHeaderFormat("")//
					.setLength("9")//
					.build());

}
