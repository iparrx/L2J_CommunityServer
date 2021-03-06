/*
 * Copyright (C) 2004-2014 L2J Server
 *
 * This file is part of L2J Server.
 *
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import javolution.text.TextBuilder;

public class ConsoleLogFormatter extends Formatter
{
	private static final String CRLF = "\r\n";
	
	@Override
	public String format(LogRecord record)
	{
		TextBuilder output = new TextBuilder();
		output.append('[');
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
		output.append(sdf.format(new Date(record.getMillis())));
		output.append(']');
		output.append(' ');
		output.append(record.getMessage());
		output.append(CRLF);
		if (record.getThrown() != null)
		{
			try (StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw))
			{
				record.getThrown().printStackTrace(pw);
				output.append(sw.toString());
				output.append(CRLF);
			}
			catch (Exception ex)
			{
				//
			}
		}
		return output.toString();
	}
}
