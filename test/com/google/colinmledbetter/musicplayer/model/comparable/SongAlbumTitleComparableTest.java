package com.google.colinmledbetter.musicplayer.model.comparable;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.colinmledbetter.musicplayer.model.Song;

public class SongAlbumTitleComparableTest {
	
	private List<Song> songList;
	
	@Before
	public void loadSongList() {
		songList = ComparableTestUtils.sortableSongs;
	}
	
	@Test
	public void testAfterSortIsSorted() {
		Collections.sort(songList, new SongAlbumTitleComparable());
		boolean isSorted = true;
		for (int i = 0; i < 10 && isSorted; i++) {
			if (!songList.get(i).getAlbumTitle().equals(String.valueOf(i))) {
				isSorted = false;
			}
		}
		Assert.assertTrue(isSorted);
	}

}
