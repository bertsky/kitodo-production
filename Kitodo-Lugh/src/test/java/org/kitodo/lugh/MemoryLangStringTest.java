/*
 * (c) Kitodo. Key to digital objects e. V. <contact@kitodo.org>
 *
 * This file is part of the Kitodo project.
 *
 * It is licensed under GNU General Public License version 3 or later.
 *
 * For the full copyright and license information, please read the
 * GPL3-License.txt file that was distributed with this source code.
 */

package org.kitodo.lugh;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MemoryLangStringTest {

    private static final String XML_LANG = "http://www.w3.org/XML/1998/namespace#lang";

    @Test
    public void testEqualsObject() {
        MemoryLangString one = new MemoryLangString("Hoc est corpus meum.", "la");
        MemoryLangString another = new MemoryLangString("Hoc est corpus meum.", "la");

        assertThat(one, is(equalTo(another)));
    }

    @Test
    public void testHashCode() {
        MemoryLangString one = new MemoryLangString("Hoc est corpus meum.", "la");
        MemoryLangString other = new MemoryLangString("Hoc est corpus meum.", "la");

        assertThat(other.hashCode(), is(equalTo(one.hashCode())));
    }

    @Test
    public void testMatchesNotOnAMemoryNodeThatRequestsADifferentLanguage() {
        MemoryLangString object = new MemoryLangString("Hoc est corpus meum.", "la");
        MemoryNode condition = new MemoryNode().put(XML_LANG, "fr");

        assertThat(object.matches(condition), is(false));
    }

    @Test
    public void testMatchesNotOnAMemoryNodeThatRequestsADifferentType() {
        MemoryLangString object = new MemoryLangString("Hoc est corpus meum.", "la");
        MemoryNode condition = new MemoryNode(Mets.FILE_SEC);

        assertThat(object.matches(condition), is(false));
    }

    @Test
    public void testMatchesNotOnAMemoryNodeThatRequestsADifferentValue() {
        MemoryLangString object = new MemoryLangString("In vino veritas est.", "la");
        MemoryNode condition = new MemoryNode().put(RDF.VALUE, "In aqua bacillus est.");

        assertThat(object.matches(condition), is(false));
    }

    @Test
    public void testMatchesOnAMemoryNodeThatRequestsAMemoryLangString() {
        MemoryLangString object = new MemoryLangString("Hoc est corpus meum.", "la");
        MemoryNode condition = new MemoryNode(RDF.LANG_STRING);

        assertThat(object.matches(condition), is(true));
    }

    @Test
    public void testMatchesOnAMemoryNodeThatRequestsTheCorrectValue() {
        MemoryLangString object = new MemoryLangString("Hoc est corpus meum.", "la");
        MemoryNode condition = new MemoryNode().put(RDF.VALUE, "Hoc est corpus meum.");

        assertThat(object.matches(condition), is(true));
    }

    @Test
    public void testMatchesOnAMemoryNodeThatRequestsTheLanguage() {
        MemoryLangString object = new MemoryLangString("Hoc est corpus meum.", "la");
        MemoryNode condition = new MemoryNode().put(XML_LANG, "la");

        assertThat(object.matches(condition), is(true));
    }

    @Test
    public void testMatchesOnAnotherMemoryLangStringIfTheyAreEqual() {
        MemoryLangString object = new MemoryLangString("Hoc est corpus meum.", "la");
        MemoryLangString condition = new MemoryLangString("Hoc est corpus meum.", "la");

        assertThat(object.matches(condition), is(true));
    }

    @Test
    public void testMemoryLangStringCanBeCreatedWithBothTextAndLanguageGiven() {
        new MemoryLangString("In vino veritas est.", "la");
    }

    @Test(expected = NullPointerException.class)
    public void testMemoryLangStringCannotBeCreatedWithoutLanguage1() {
        new MemoryLangString("In aqua bacillus est.", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMemoryLangStringCannotBeCreatedWithoutLanguage2() {
        new MemoryLangString("In aqua bacillus est.", "");
    }

    @Test
    public void testToString() {
        MemoryLangString MemoryLangString = new MemoryLangString(
                "Control characters like \r and \n should be visible in the toString() result.", "en");

        assertEquals("\"Control characters like \\I and \\F should be visible in the toString() result.\"@en",
                MemoryLangString.toString());
    }
}
