/*******************************************************************************
 * Copyright (c) 2022 - 2025 Maxprograms.
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

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class PI implements XMLNode {

	private static final long serialVersionUID = -689697302415200547L;
	private String target;
	private String data;

	public PI(String target, String data) {
		this.target = target;
		this.data = data;
	}

	public String getTarget() {
		return target;
	}

	public String getData() {
		return data;
	}

	public void setData(String value) {
		data = value;
	}

	@Override
	public String toString() {
		return "<?" + target + " " + data + "?>";
	}

	@Override
	public void writeBytes(OutputStream output, Charset charset) throws IOException {
		output.write(toString().getBytes(charset));
	}

	@Override
	public short getNodeType() {
		return XMLNode.PROCESSING_INSTRUCTION_NODE;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PI)) {
			return false;
		}
		PI pi = (PI) obj;
		return target.equals(pi.getTarget()) && data.equals(pi.getData());
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

}
