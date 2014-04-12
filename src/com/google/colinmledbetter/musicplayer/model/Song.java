package com.google.colinmledbetter.musicplayer.model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;

import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
import com.google.colinmledbetter.musicplayer.model.exceptions.UninteractableSongException;
import com.google.common.collect.Lists;

public class Song {

	//formatter:off
	private static final String
			UNREADABLE_MESSAGE = "File either does not exist or is unreadable: ",
			UNWRITABLE_MESSAGE = "The application has no write access: ",
			INVALID_TAG_MESSAGE = "The tag for this file was corrupt: ",
			INVALID_FRAME_MESSAGE = "The audio frame for this file was corrupt: ";

	private static final String
			UNKNOWN_SONG_TITLE = "Unknown Song",
			UNKNOWN_ARTIST_TITLE = "Unknown Artist",
			UNKNOWN_ALBUM_TITLE = "Unknown Album",
			UNKNOWN_SONG_NUMBER = "",
			UNKNOWN_SONG_DISK_NUMBER = "",
			UNKNOWN_SONG_YEAR = "Unknown Year";

	private static final List<String>
			invalidSongTitles = Lists.newArrayList(""),
			invalidArtistTitles = Lists.newArrayList(""),
			invalidAlbumTitles = Lists.newArrayList(""), //
			invalidAlbumArtistTitles = Lists.newArrayList(""),
			invalidSongNumbers = Lists.newArrayList("0", "-1"),
			invalidSongDiskNumbers = Lists.newArrayList("0", "-1"),
			invalidSongYears = Lists.newArrayList("0", "-1", "");
	//formatter:on

	private String songTitle;
	private String artistTitle;
	private String albumTitle;
	private String albumArtistTitle;
	private String songNumber;
	private String songDiskNumber;
	private String songYear;
	private int songTime;

	public Song(String filepath) throws UninteractableSongException,
			CorruptSongException {
		try {
			AudioFile file = AudioFileIO.read(new File(filepath));
			loadInfoFromTag(file.getTag());
			loadInfoFromHeader(file.getAudioHeader());
		} catch (CannotReadException | IOException e) {
			throw new UninteractableSongException(UNREADABLE_MESSAGE + filepath);
		} catch (ReadOnlyFileException e) {
			throw new UninteractableSongException(UNWRITABLE_MESSAGE + filepath);
		} catch (TagException e) {
			throw new CorruptSongException(INVALID_TAG_MESSAGE + filepath);
		} catch (InvalidAudioFrameException e) {
			throw new CorruptSongException(INVALID_FRAME_MESSAGE + filepath);
		}
	}

	private void loadInfoFromTag(Tag tag) {
		songTitle = loadFieldKeyFromTag(FieldKey.TITLE, tag, //
				invalidSongTitles, UNKNOWN_SONG_TITLE);
		artistTitle = loadFieldKeyFromTag(FieldKey.ARTIST, tag, //
				invalidArtistTitles, UNKNOWN_ARTIST_TITLE);
		albumTitle = loadFieldKeyFromTag(FieldKey.ALBUM, tag, //
				invalidAlbumTitles, UNKNOWN_ALBUM_TITLE);
		albumArtistTitle = loadFieldKeyFromTag(FieldKey.ALBUM_ARTIST, tag, //
				invalidAlbumArtistTitles, artistTitle);
		songNumber = loadFieldKeyFromTag(FieldKey.TRACK, tag, //
				invalidSongNumbers, UNKNOWN_SONG_NUMBER);
		songDiskNumber = loadFieldKeyFromTag(FieldKey.DISC_NO, tag, //
				invalidSongDiskNumbers, UNKNOWN_SONG_DISK_NUMBER);
		songYear = loadFieldKeyFromTag(FieldKey.YEAR, tag, //
				invalidSongYears, UNKNOWN_SONG_YEAR);
	}

	private static String loadFieldKeyFromTag(FieldKey key, Tag tag,
			List<String> invalid, String onInvalid) {
		try {
			String returnable = tag.getFirst(key);
			if (isStringValid(returnable, invalid)) {
				return returnable;
			} else {
				return onInvalid;
			}
		} catch (UnsupportedOperationException e) {
			return onInvalid;
		} catch (NullPointerException e) {
			return onInvalid;
		}
	}

	private static boolean isStringValid(String value,
			List<String> invalidStrings) {
		for (String invalidString : invalidStrings) {
			if (value.equals(invalidString)) {
				return false;
			}
		}
		return true;
	}

	private void loadInfoFromHeader(AudioHeader header) {
		songTime = header.getTrackLength();
	}
	
	public String getSongTitle() {
		return songTitle;
	}
	
	public String getArtistTitle() {
		return artistTitle;
	}
	
	public String getAlbumTitle() {
		return albumTitle;
	}
	
	public String getAlbumArtistTitle() {
		return albumArtistTitle;
	}
	
	public String getSongNumber() {
		return songNumber;
	}
	
	public String getSongDiskNumber() {
		return songDiskNumber;
	}
	
	public String getSongYear() {
		return songYear;
	}
	
	public int getSongTimeInSeconds() {
		return songTime;
	}

}
