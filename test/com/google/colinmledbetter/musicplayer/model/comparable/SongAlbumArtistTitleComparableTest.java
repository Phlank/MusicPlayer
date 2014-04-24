package com.google.colinmledbetter.musicplayer.model.comparable;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.colinmledbetter.musicplayer.model.Song;
import com.google.colinmledbetter.musicplayer.model.TestUtils;

public class SongAlbumArtistTitleComparableTest {

	private List<Song> songList;

	@Before
	public void loadSongList() {
		songList = TestUtils.sortableSongs;
	}

	@Test
	public void testAfterSortIsSorted() {
		Collections.sort(songList, new SongAlbumArtistTitleComparable());
		boolean isSorted = true;
		for (int i = 0; i < 10 && isSorted; i++) {
			if (!songList.get(i).getAlbumArtistTitle()
					.equals(String.valueOf(i))) {
				isSorted = false;
			}
		}
		Assert.assertTrue(isSorted);
	}

}
