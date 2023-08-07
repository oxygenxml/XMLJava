/*******************************************************************************
 * Copyright (c) 2023 Maxprograms.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-v10.html
 *
 * Contributors:
 *     Maxprograms - initial API and implementation
 *******************************************************************************/
package com.maxprograms.xml;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Indenter {

	private static int level;
	private static int numSpaces;

	private Indenter() {
		// do not instantiate
	}

	public static void indent(Element e, int indent, int spaces) {
		level = indent;
		numSpaces = spaces;
		recurse(e);
	}

	public static void indent(Element e, int spaces) {
		level = 1;
		numSpaces = spaces;
		recurse(e);
	}

	private static void recurse(Element e) {
		if ("preserve".equals(e.getAttributeValue("xml:space"))) {
			return;
		}
		if (!hasText(e)) {
			indent(e);
		}
		level++;
		List<Element> children = e.getChildren();
		Iterator<Element> it = children.iterator();
		while (it.hasNext()) {
			recurse(it.next());
		}
		level--;
	}

	private static void indent(Element e) {
		StringBuilder start = new StringBuilder("\n");
		StringBuilder end = new StringBuilder("\n");
		for (int i = 0; i < (level * numSpaces); i++) {
			start.append(' ');
		}
		for (int i = 0; i < ((level - 1) * numSpaces); i++) {
			end.append(' ');
		}
		List<XMLNode> content = new Vector<>();
		List<XMLNode> nodes = e.getContent();
		Iterator<XMLNode> it = nodes.iterator();
		while (it.hasNext()) {
			XMLNode node = it.next();
			if (node.getNodeType() != XMLNode.TEXT_NODE) {
				content.add(new TextNode(start.toString()));
				content.add(node);
			}
		}
		if (!content.isEmpty()) {
			content.add(new TextNode(end.toString()));
		}
		e.setContent(content);
	}

	private static boolean hasText(Element e) {
		List<XMLNode> nodes = e.getContent();
		Iterator<XMLNode> it = nodes.iterator();
		while (it.hasNext()) {
			XMLNode node = it.next();
			if (node.getNodeType() == XMLNode.TEXT_NODE) {
				TextNode t = (TextNode) node;
				String text = t.getText();
				if (text != null) {
					for (int i = 0; i < text.length(); i++) {
						char c = text.charAt(i);
						if (!(Character.isSpaceChar(c) || c == '\n')) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
