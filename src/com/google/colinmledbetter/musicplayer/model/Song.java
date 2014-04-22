package com.google.colinmledbetter.musicplayer.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;

import com.google.colinmledbetter.musicplayer.model.exceptions.CorruptSongException;
import com.google.colinmledbetter.musicplayer.model.exceptions.UninteractableSongException;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class Song {

	public static class Builder {

		private String filepath;
		private String title;
		private String artistTitle;
		private String albumTitle;
		private String albumArtistTitle;
		private String number;
		private String diskNumber;
		private String year;
		private String length;
		private String headerFormat;

		public Builder() {
		}

		public Builder setFilepath(String filepath) {
			this.filepath = filepath;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setArtistTitle(String artistTitle) {
			this.artistTitle = artistTitle;
			return this;
		}

		public Builder setAlbumTitle(String albumTitle) {
			this.albumTitle = albumTitle;
			return this;
		}

		public Builder setAlbumArtistTitle(String albumArtistTitle) {
			this.albumArtistTitle = albumArtistTitle;
			return this;
		}

		public Builder setNumber(String number) {
			this.number = number;
			return this;
		}

		public Builder setDiskNumber(String diskNumber) {
			this.diskNumber = diskNumber;
			return this;
		}

		public Builder setYear(String year) {
			this.year = year;
			return this;
		}

		public Builder setLength(String length) {
			this.length = length;
			return this;
		}

		public Builder setHeaderFormat(String headerFormat) {
			this.headerFormat = headerFormat;
			return this;
		}

		public Song build() {
			return new Song(Preconditions.checkNotNull(filepath),//
					Preconditions.checkNotNull(title),//
					Preconditions.checkNotNull(artistTitle),//
					Preconditions.checkNotNull(albumTitle),//
					Preconditions.checkNotNull(albumArtistTitle),//
					Preconditions.checkNotNull(number),//
					Preconditions.checkNotNull(diskNumber),//
					Preconditions.checkNotNull(year),//
					Preconditions.checkNotNull(length),//
					Preconditions.checkNotNull(headerFormat));
		}

	}

	private static final String UNREADABLE_MESSAGE = "File either does not exist or is unreadable: ";
	private static final String UNWRITABLE_MESSAGE = "The application has no write access: ";
	private static final String INVALID_TAG_MESSAGE = "The tag for this file was corrupt: ";
	private static final String INVALID_FRAME_MESSAGE = "The audio frame for this file was corrupt: ";

	private static final String ARTWORK_READ_UNREADABLE_MESSAGE = "Could not read the artwork from this file: ";
	private static final String ARTWORK_READ_CORRUPT_MESSAGE = "The audio file is corrupted and unable to be opened for reading artwork: ";
	private static final String ARTWORK_READ_FORMAT_MESSAGE = "Artwork is not supported for the audio format ";

	private static final String ARTWORK_WRITE_FORMAT_MESSAGE = "Writing artwork is not supported for the audio format ";
	private static final String ARTWORK_WRITE_IO_MESSAGE = "Unable to read the file for writing artwork: ";
	private static final String ARTWORK_WRITE_CORRUPT_MESSAGE = "The audio file is corrupted and unable to be opened for writing artwork: ";
	private static final String ARTWORK_WRITE_UNWRITEABLE_MESSAGE = "The program does not have permission to alter the file: ";

	private static final String UNKNOWN_SONG_TITLE = "Unknown Song";
	private static final String UNKNOWN_ARTIST_TITLE = "Unknown Artist";
	private static final String UNKNOWN_ALBUM_TITLE = "Unknown Album";
	private static final String UNKNOWN_SONG_NUMBER = "";
	private static final String UNKNOWN_SONG_DISK_NUMBER = "";
	private static final String UNKNOWN_SONG_YEAR = "Unknown Year";

	private static final List<String> INVALID_TITLES = //
	Lists.newArrayList("");
	private static final List<String> INVALID_ARTIST_TITLES = //
	Lists.newArrayList("");
	private static final List<String> INVALID_ALBUM_TITLES = //
	Lists.newArrayList("");
	private static final List<String> INVALID_ALBUM_ARTIST_TITLES = //
	Lists.newArrayList("");
	private static final List<String> INVALID_NUMBERS = //
	Lists.newArrayList("0", "-1");
	private static final List<String> INVALID_DISK_NUMBERS = //
	Lists.newArrayList("0", "-1");
	private static final List<String> INVALID_YEARS = //
	Lists.newArrayList("0", "-1", "");

	private String filepath;
	private String title;
	private String artistTitle;
	private String albumTitle;
	private String albumArtistTitle;
	private String number;
	private String diskNumber;
	private String year;
	private int length;
	private String headerFormat;
	private SongFormat enumFormat;

	private Song(String filepath, String title, String artistTitle,
			String albumTitle, String albumArtistTitle, String number,
			String diskNumber, String year, String length, String headerFormat) {
		this.filepath = filepath;
		this.title = title;
		this.artistTitle = artistTitle;
		this.albumTitle = albumTitle;
		this.albumArtistTitle = albumArtistTitle;
		this.number = number;
		this.diskNumber = diskNumber;
		this.year = year;
		this.length = Integer.parseInt(length);
		this.headerFormat = headerFormat;
		this.enumFormat = SongFormat.headerFormatToEnumFormat(headerFormat);
	}

	public Song(String filepath) throws UninteractableSongException,
			CorruptSongException {
		try {
			this.filepath = filepath;
			AudioFile file = AudioFileIO.read(new File(filepath));
			loadFieldsFromTag(file.getTag());
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

	private void loadFieldsFromTag(Tag tag) {
		loadFields(tag);
	}

	private void loadFields(Tag tag) {
		title = loadFieldKeyFromTag(FieldKey.TITLE, tag, //
				INVALID_TITLES, UNKNOWN_SONG_TITLE);
		artistTitle = loadFieldKeyFromTag(FieldKey.ARTIST, tag, //
				INVALID_ARTIST_TITLES, UNKNOWN_ARTIST_TITLE);
		albumTitle = loadFieldKeyFromTag(FieldKey.ALBUM, tag, //
				INVALID_ALBUM_TITLES, UNKNOWN_ALBUM_TITLE);
		albumArtistTitle = loadFieldKeyFromTag(FieldKey.ALBUM_ARTIST, tag, //
				INVALID_ALBUM_ARTIST_TITLES, artistTitle);
		number = loadFieldKeyFromTag(FieldKey.TRACK, tag, //
				INVALID_NUMBERS, UNKNOWN_SONG_NUMBER);
		diskNumber = loadFieldKeyFromTag(FieldKey.DISC_NO, tag, //
				INVALID_DISK_NUMBERS, UNKNOWN_SONG_DISK_NUMBER);
		year = loadFieldKeyFromTag(FieldKey.YEAR, tag, //
				INVALID_YEARS, UNKNOWN_SONG_YEAR);
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
		length = header.getTrackLength();
		headerFormat = header.getFormat();
		enumFormat = SongFormat.headerFormatToEnumFormat(headerFormat);
	}

	public String getFilepath() {
		return filepath;
	}

	public String getTitle() {
		return title;
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

	public String getNumber() {
		return number;
	}

	public String getDiskNumber() {
		return diskNumber;
	}

	public String getYear() {
		return year;
	}

	public int getLength() {
		return length;
	}

	public SongFormat getFormat() {
		return enumFormat;
	}

	public String getHeaderFormat() {
		return headerFormat;
	}

	public void setTitle(String songTitle) {
		this.title = songTitle;
	}

	public void setArtistTitle(String artistTitle) {
		this.artistTitle = artistTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public void setAlbumArtistTitle(String albumArtistTitle) {
		this.albumArtistTitle = albumArtistTitle;
	}

	public void setNumber(String songNumber) {
		this.number = songNumber;
	}

	public void setDiskNumber(String songDiskNumber) {
		this.diskNumber = songDiskNumber;
	}

	public void setYear(String songYear) {
		this.year = songYear;
	}

	public BufferedImage getArtwork() throws UninteractableSongException,
			CorruptSongException {
		try {
			return (BufferedImage) AudioFileIO.read(new File(filepath))
					.getTag().getFirstArtwork().getImage();
		} catch (IOException | CannotReadException | ReadOnlyFileException e) {
			String message = ARTWORK_READ_UNREADABLE_MESSAGE + filepath;
			throw new UninteractableSongException(message);
		} catch (TagException | InvalidAudioFrameException e) {
			String message = ARTWORK_READ_CORRUPT_MESSAGE + filepath;
			throw new CorruptSongException(message);
		} catch (UnsupportedOperationException e) {
			String message = ARTWORK_READ_FORMAT_MESSAGE + headerFormat
					+ " at file: " + filepath;
			throw new UninteractableSongException(message);
		}
	}

	public void writeArtwork(BufferedImage image)
			throws UninteractableSongException, IOException,
			CorruptSongException {
		Tag tag;
		File writeFile;
		Artwork artwork;
		AudioFile songFile;
		try {
			tag = AudioFileIO.read(new File(filepath)).getTag();
			tag.deleteArtworkField();
			writeFile = new File("test-assets/emptyfile");
			ImageIO.write(image, "png", writeFile);
			artwork = ArtworkFactory.createArtworkFromFile(writeFile);
			tag.setField(artwork);
			songFile = AudioFileIO.read(new File(filepath));
			songFile.setTag(tag);
			AudioFileIO.write(songFile);
		} catch (UnsupportedOperationException e) {
			String message = ARTWORK_WRITE_FORMAT_MESSAGE
					+ this.getHeaderFormat() + " at file: " + filepath;
			throw new UninteractableSongException(message);
		} catch (IOException | CannotReadException e) {
			String message = ARTWORK_WRITE_IO_MESSAGE + filepath;
			throw new IOException(message);
		} catch (TagException | InvalidAudioFrameException e) {
			String message = ARTWORK_WRITE_CORRUPT_MESSAGE + filepath;
			throw new CorruptSongException(message);
		} catch (ReadOnlyFileException | CannotWriteException e) {
			String message = ARTWORK_WRITE_UNWRITEABLE_MESSAGE + filepath;
			throw new UninteractableSongException(message);
		}
	}

	@Override
	public String toString() {
		return getTitle();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj.getClass().equals(Song.class)) {
			Song other = (Song) obj;
			return Objects.equal(filepath, other.filepath)
					&& Objects.equal(title, other.title)
					&& Objects.equal(artistTitle, other.artistTitle)
					&& Objects.equal(albumTitle, other.albumTitle)
					&& Objects.equal(albumArtistTitle, other.albumArtistTitle)
					&& Objects.equal(number, other.number)
					&& Objects.equal(diskNumber, other.diskNumber)
					&& Objects.equal(year, other.year)
					&& Objects.equal(length, other.length);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(filepath, title, artistTitle, albumTitle,
				albumArtistTitle, number, diskNumber, year, length);
	}

}
