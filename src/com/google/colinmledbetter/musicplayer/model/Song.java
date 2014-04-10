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
	private static final String UNKNOWN_SONG_TITLE = "Unknown Song";
	private static final String UNKNOWN_ARTIST_TITLE = "Unknown Artist";
	private static final String UNKNOWN_ALBUM_TITLE = "Unknown Album";

	private Tag tag;
	private AudioHeader header;

	public Song(String filepath) throws UnreadableSongException {
		try {
			AudioFile file = AudioFileIO.read(new File(filepath));
			tag = file.getTag();
			header = file.getAudioHeader();
		} catch (CannotReadException | IOException | ReadOnlyFileException
				| TagException | InvalidAudioFrameException e) {
			throw new UnreadableSongException(UNREADABLE_MESSAGE);
		}
	}

	public String getSongTitle() {
		boolean hasSongTitle = tag != null && //
				!tag.getFirst(FieldKey.TITLE).equals("");
		if (hasSongTitle) {
			return tag.getFirst(FieldKey.TITLE);
		} else {
			return UNKNOWN_SONG_TITLE;
		}
	}

	public String getArtistTitle() {
		boolean hasArtistTitle = tag != null && //
				!tag.getFirst(FieldKey.ARTIST).equals("");
		if (hasArtistTitle) {
			return tag.getFirst(FieldKey.ARTIST);
		} else {
			return UNKNOWN_ARTIST_TITLE;
		}
	}

	public String getAlbumTitle() {
		boolean hasAlbumTitle = tag != null && //
				!tag.getFirst(FieldKey.ALBUM).equals("");
		if (hasAlbumTitle) {
			return tag.getFirst(FieldKey.ALBUM);
		} else {
			return UNKNOWN_ALBUM_TITLE;
		}
	}

	public String getAlbumArtistTitle() {
		boolean formatIsUnsupported = header.getFormat().substring(0, 3)
				.equals("WAV");
		boolean hasAlbumArtistTitle = !formatIsUnsupported && //
				tag != null && //
				!tag.getFirst(FieldKey.ALBUM_ARTIST).equals("");
		if (hasAlbumArtistTitle) {
			return tag.getFirst(FieldKey.ALBUM_ARTIST);
		} else {
			return getArtistTitle();
		}
	}

	public int getNumberOfSeconds() {
		return header.getTrackLength();
	}
}
