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

package org.kitodo.dataeditor.ruleset.xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * A container for the properties of the XML element {@code <codomain>} in the
 * ruleset file.
 */
class CodomainElement {
    /**
     * The type of the codomain.
     */
    @XmlAttribute
    private Type type;

    /**
     * The name space for URIs.
     */
    @XmlAttribute
    private String namespace;

    /**
     * Returns the name space for URIs.
     *
     * @return the name space for URIs
     */
    String getNamespace() {
        return namespace;
    }

    /**
     * Returns the type of the codomain.
     *
     * @return the type of the codomain
     */
    Type getType() {
        return type;
    }
}
