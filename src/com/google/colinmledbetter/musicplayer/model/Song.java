package com.google.colinmledbetter.musicplayer.model;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;

public class Song {

	private static final String UNREADABLE_MESSAGE = "File either does not exist or is unreadable";
	private static final String CORRUPT_MESSAGE = "File is corrupt";
	private static final String UNKNOWN_SONG_TITLE = "Unknown Song";
	private static final String UNKNOWN_ARTIST_TITLE = "Unknown Artist";
	private static final String UNKNOWN_ALBUM_TITLE = "Unknown Album";
	private static final String UNKNOWN_ALBUM_ARTIST_TITLE = "Unknown Artist";

	private Tag tag;

	public Song(String filepath) throws UnreadableSongException {
		AudioFile file;
		try {
			file = AudioFileIO.read(new File(filepath));
			tag = file.getTag();
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
		if (tag.getFirst(FieldKey.ALBUM_ARTIST) == "") {
			return UNKNOWN_ALBUM_ARTIST_TITLE;
		} else {
			return tag.getFirst(FieldKey.ALBUM_ARTIST);
		}
	}

}
