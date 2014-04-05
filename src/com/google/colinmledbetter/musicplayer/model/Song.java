package com.google.colinmledbetter.musicplayer.model;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;

import com.google.colinmledbetter.musicplayer.model.exceptions.UnreadableSongException;

public class Song {

	private static final String UNREADABLE_MESSAGE = "File either does not exist or is unreadable";
	private static final String CORRUPT_MESSAGE = "File is corrupt";
	private static final String UNKNOWN_SONG_TITLE = "Unknown Song";
	private static final String UNKNOWN_ARTIST_TITLE = "Unknown Artist";
	private static final String UNKNOWN_ALBUM_TITLE = "Unknown Album";

	private Tag tag;
	private AudioHeader header;
	private String formattedTrackLength;

	private int numberOfHours;
	private int numberOfMinutes;
	private int numberOfSeconds;

	public Song(String filepath) throws UnreadableSongException {
		AudioFile file;
		try {
			file = AudioFileIO.read(new File(filepath));
			tag = file.getTag();
			header = file.getAudioHeader();
		} catch (TagException | InvalidAudioFrameException e) {
			throw new UnreadableSongException(CORRUPT_MESSAGE);
		} catch (CannotReadException | IOException | ReadOnlyFileException e) {
			throw new UnreadableSongException(UNREADABLE_MESSAGE);
		}
	}

	public String getSongTitle() {
		if (tag.getFirst(FieldKey.TITLE) == "") {
			return UNKNOWN_SONG_TITLE;
		} else {
			return tag.getFirst(FieldKey.TITLE);
		}
	}

	public String getArtistTitle() {
		if (tag.getFirst(FieldKey.ARTIST) == "") {
			return UNKNOWN_ARTIST_TITLE;
		} else {
			return tag.getFirst(FieldKey.ARTIST);
		}
	}

	public String getAlbumTitle() {
		if (tag.getFirst(FieldKey.ALBUM) == "") {
			return UNKNOWN_ALBUM_TITLE;
		} else {
			return tag.getFirst(FieldKey.ALBUM);
		}
	}

	public String getAlbumArtistTitle() {
		try {
			if (tag.getFirst(FieldKey.ALBUM_ARTIST) == "") {
				return getArtistTitle();
			} else {
				return tag.getFirst(FieldKey.ALBUM_ARTIST);
			}
		} catch (UnsupportedOperationException e) {
			return getArtistTitle();
		}
	}

	public String getFormattedTrackLength() {
		numberOfSeconds = header.getTrackLength() % 60;
		numberOfMinutes = (header.getTrackLength() / 60) % 60;
		numberOfHours = header.getTrackLength() / 3600;
		formattedTrackLength = "";
		appendHours();
		appendMinutes();
		appendSeconds();
		return formattedTrackLength;
	}

	private void appendHours() {
		if (numberOfHours > 0) {
			formattedTrackLength = formattedTrackLength + numberOfHours + ":";
		}
	}

	private void appendMinutes() {
		if (numberOfHours > 0 && numberOfMinutes < 10) {
			formattedTrackLength = formattedTrackLength + "0" + numberOfMinutes
					+ ":";
		} else if (numberOfMinutes > 0) {
			formattedTrackLength = formattedTrackLength + numberOfMinutes + ":";
		}
	}

	private void appendSeconds() {
		if (numberOfMinutes > 0 && numberOfSeconds < 10) {
			formattedTrackLength = formattedTrackLength + "0" + numberOfSeconds;
		} else {
			formattedTrackLength = formattedTrackLength + numberOfSeconds;
		}
	}
}
