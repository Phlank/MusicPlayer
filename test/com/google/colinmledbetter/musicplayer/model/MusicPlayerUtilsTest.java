package com.google.colinmledbetter.musicplayer.model;

import org.junit.Test;
import org.junit.Assert;

public class MusicPlayerUtilsTest {
	
	@Test
	public void testFiveSecondsFormatsTo00005() {
		Assert.assertEquals("0:00:05", MusicPlayerUtils.secondsToTime(5));
	}

}
