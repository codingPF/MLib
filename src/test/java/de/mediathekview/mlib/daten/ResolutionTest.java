/*
 * ResolutionTest.java
 *
 * Projekt    : MLib
 * erstellt am: 18.09.2017
 * Autor      : Sascha
 *
 * (c) 2017 by Sascha Wiegandt
 */
package de.mediathekview.mlib.daten;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResolutionTest {

  @Rule public ExpectedException exception = ExpectedException.none();

  @Test
  public void testGetHigherResolutionThanVerySmall() {
    assertEquals(Resolution.SMALL, Resolution.getNextHigherResolution(Resolution.VERY_SMALL));
  }

  @Test
  public void testGetHigherResolutionThanSmall() {
    assertEquals(Resolution.NORMAL, Resolution.getNextHigherResolution(Resolution.SMALL));
  }

  @Test
  public void testGetHigherResolutionThanNormal() {
    assertEquals(Resolution.HD, Resolution.getNextHigherResolution(Resolution.NORMAL));
  }

  @Test
  public void tryGettingHigherResolutionThanUhdWithStopAtUhd() {
    assertEquals(Resolution.UHD, Resolution.getNextHigherResolution(Resolution.UHD));
  }

  @Test
  public void tryGettingHigherResolutionThanWqhd() {
    assertEquals(Resolution.UHD, Resolution.getNextHigherResolution(Resolution.WQHD));
  }

  @Test
  public void tryGettingHigherResolutionThanHd() {
    assertEquals(Resolution.WQHD, Resolution.getNextHigherResolution(Resolution.HD));
  }

  @Test
  public void testGetLowerResolutionThanHd() {
    assertEquals(Resolution.NORMAL, Resolution.getNextLowerResolution(Resolution.HD));
  }

  @Test
  public void testGetLowerResolutionThanNormal() {
    assertEquals(Resolution.SMALL, Resolution.getNextLowerResolution(Resolution.NORMAL));
  }

  @Test
  public void testGetLowerResolutionThanSmall() {
    assertEquals(Resolution.VERY_SMALL, Resolution.getNextLowerResolution(Resolution.SMALL));
  }

  @Test
  public void tryGettingLowerResolutionThanVerySmallWithStopAtVerySmall() {
    assertEquals(Resolution.VERY_SMALL, Resolution.getNextLowerResolution(Resolution.VERY_SMALL));
  }

  @Test
  public void testResolutionTextVerySmall() {
    assertEquals("Sehr klein", Resolution.VERY_SMALL.getDescription());
  }

  @Test
  public void testResolutionTextSmall() {
    assertEquals("Klein", Resolution.SMALL.getDescription());
  }

  @Test
  public void testResolutionTextNormal() {
    assertEquals("Normal", Resolution.NORMAL.getDescription());
  }

  @Test
  public void testResolutionTextHd() {
    assertEquals("HD", Resolution.HD.getDescription());
  }

  @Test
  public void testGetReversedListOfResoltions() {
    final List<Resolution> descendingList = Resolution.getFromBestToLowest();

    assertEquals(6, descendingList.size());

    assertEquals(Resolution.UHD, descendingList.get(0));
    assertEquals(Resolution.WQHD, descendingList.get(1));
    assertEquals(Resolution.HD, descendingList.get(2));
    assertEquals(Resolution.NORMAL, descendingList.get(3));
    assertEquals(Resolution.SMALL, descendingList.get(4));
    assertEquals(Resolution.VERY_SMALL, descendingList.get(5));
  }

  @Test
  public void testGetHighestResolution() {
    assertEquals(Resolution.UHD, Resolution.getHighestResolution());
  }

  @Test
  public void testGetLowestResolution() {
    assertEquals(Resolution.VERY_SMALL, Resolution.getLowestResolution());
  }

  @Test
  public void testGetUnknownResoultionByResolutionSize() {
    exception.expect(NoSuchElementException.class);
    exception.expectMessage("Resolution with ResolutionIndex 42 not found");
    assertNull(Resolution.getResoultionByResolutionSize(42));
  }
}
