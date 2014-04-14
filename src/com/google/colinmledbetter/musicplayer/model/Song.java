package com.google.colinmledbetter.musicplayer.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;

import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
import com.google.colinmledbetter.musicplayer.model.exceptions.UninteractableSongException;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class Song {

	private static final String UNREADABLE_MESSAGE =
			"File either does not exist or is unreadable: ";
	private static final String UNWRITABLE_MESSAGE =
			"The application has no write access: ";
	private static final String INVALID_TAG_MESSAGE =
			"The tag for this file was corrupt: ";
	private static final String INVALID_FRAME_MESSAGE =
			"The audio frame for this file was corrupt: ";

	private static final String UNKNOWN_SONG_TITLE = "Unknown Song";
	private static final String UNKNOWN_ARTIST_TITLE = "Unknown Artist";
	private static final String UNKNOWN_ALBUM_TITLE = "Unknown Album";
	private static final String UNKNOWN_SONG_NUMBER = "";
	private static final String UNKNOWN_SONG_DISK_NUMBER = "";
	private static final String UNKNOWN_SONG_YEAR = "Unknown Year";

	private static final List<String> invalidSongTitles = //
			Lists.newArrayList("");
	private static final List<String> invalidArtistTitles = //
			Lists.newArrayList("");
	private static final List<String> invalidAlbumTitles = //
			Lists.newArrayList("");
	private static final List<String> invalidAlbumArtistTitles = //
			Lists.newArrayList("");
	private static final List<String> invalidSongNumbers = //
			Lists.newArrayList("0", "-1");
	private static final List<String> invalidSongDiskNumbers = //
			Lists.newArrayList("0", "-1");
	private static final List<String> invalidSongYears = //
			Lists.newArrayList("0", "-1", "");

	private String filepath;
	private String songTitle;
	private String artistTitle;
	private String albumTitle;
	private String albumArtistTitle;
	private String songNumber;
	private String songDiskNumber;
	private String songYear;
	private int songTime;
	private SongFormat songFormat;

	public Song(String filepath)
			throws UninteractableSongException, CorruptSongException {
		try {
			this.filepath = filepath;
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
		loadSongFields(tag);
	}

	private void loadSongFields(Tag tag) {
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
		} catch (UnsupportedOperationException | NullPointerException e) {
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
		songFormat = getFormatFromHeader(header);
	}
	
	private SongFormat getFormatFromHeader(AudioHeader header) {
		System.out.println(header.getFormat());
		String formatString = header.getFormat();
		
		
	}

	public String getFilepath() {
		return filepath;
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

	public BufferedImage getArtworkAsBufferedImage() {
		try {
			return (BufferedImage) AudioFileIO.read(new File(filepath))
					.getTag().getFirstArtwork().getImage();
		} catch (
				IOException
				| CannotReadException
				| TagException
				| ReadOnlyFileException
				| InvalidAudioFrameException e) {
			return null;
		}
	}

	public void writeArtwork(BufferedImage image) throws UninteractableSongException {
		try {
			Tag tag = AudioFileIO.read(new File(filepath)).getTag();
			tag.deleteArtworkField();
			File temp = new File("test-assets/emptyfile");
			ImageIO.write(image, "png", temp);
			Artwork artwork = ArtworkFactory.createArtworkFromFile(temp);
			tag.setField(artwork);
			AudioFile file = AudioFileIO.read(new File(filepath));
			file.setTag(tag);
			AudioFileIO.write(file);
		} catch (Exception e) {
			throw new UninteractableSongException("Could not write artwork to file: " + filepath);
		}
	}

	@Override
	public String toString() {
		return getSongTitle();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj.getClass().equals(Song.class)) {
			Song other = (Song) obj;
			return Objects.equal(this.filepath, other.filepath)
					&& Objects.equal(this.songTitle, other.songTitle)
					&& Objects.equal(this.artistTitle, other.artistTitle)
					&& Objects.equal(this.albumTitle, other.albumTitle)
					&& Objects.equal(this.albumArtistTitle,
							other.albumArtistTitle)
					&& Objects.equal(this.songNumber, other.songNumber)
					&& Objects.equal(this.songDiskNumber, other.songDiskNumber)
					&& Objects.equal(this.songYear, other.songYear)
					&& Objects.equal(this.songTime, other.songTime);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.filepath, this.songTitle,
				this.artistTitle, this.albumTitle, this.albumArtistTitle,
				this.songNumber, this.songDiskNumber, this.songYear,
				this.songTime);
	}

}
