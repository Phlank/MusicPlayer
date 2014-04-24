package com.github.phlank.musicplayer.model.comparable;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.phlank.musicplayer.model.Song;
import com.github.phlank.musicplayer.model.TestUtils;

public class SongArtistTitleComparableTest {

	private List<Song> songList;

	@Before
	public void loadSongList() {
		songList = TestUtils.sortableSongs;
	}

	@Test
	public void testAfterSortIsSorted() {
		Collections.sort(songList, new SongArtistTitleComparable());
		boolean isSorted = true;
		for (int i = 0; i < 10 && isSorted; i++) {
			if (!songList.get(i).getArtistTitle().equals(String.valueOf(i))) {
				isSorted = false;
			}
		}
		Assert.assertTrue(isSorted);
	}

}
