package org.geogebra.desktop.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.geogebra.common.util.debug.Log;

/**
 * Web implementation for the GeoGebraLogger
 * 
 * @author Zoltan Kovacs 
 */
public class LoggerD extends Log {

	private File logFile = null; // default
	private BufferedWriter logFileWriter = null;

	/**
	 * Constructor
	 */
	public LoggerD() {
		keepLog = true;
	}

	@Override
	public void log(Level level, Object logMessage, int depth) {
		String message = logMessage + "";

		if (logLevel.ordinal() >= level.ordinal()) {
			String caller = "";
			if (callerShown) {
				caller = getCaller(depth);
			}
			String timeInfo = "";
			if (timeShown) {
				timeInfo = getTimeInfo();
				if (!"".equals(timeInfo)) {
					timeInfo += " ";
				}
			}
			// Creating logEntry
			String logEntry = timeInfo;
			if (levelShown) {
				logEntry += level.toString() + ": ";
			}
			logEntry += caller + message;
			print(logEntry, level);
			// In desktop logging, preserve the entire log in memory as well:
			if (keepLog) {
				if (memoryLog.length() > LOGFILE_MAXLENGTH) {
					memoryLog.setLength(0);
				}
				memoryLog.append(logEntry);
				memoryLog.append("\n");
			}
		}
	}

	private String getTimeInfo() {
		Calendar calendar = new GregorianCalendar();
		int min = calendar.get(Calendar.MINUTE);
		String minS = (min < 10) ? "0" + min : "" + min;
		int sec = calendar.get(Calendar.SECOND);
		String secS = (sec < 10) ? "0" + sec : "" + sec;
		int msec = calendar.get(Calendar.MILLISECOND);
		String msecS = (msec < 100) ? "0" + msec : "" + msec;
		if (msec < 10) {
			msecS = "0" + msecS;
		}
		return calendar.get(Calendar.HOUR_OF_DAY) + ":" + minS + ":" + secS
				+ "." + msecS;
	}

	public String getCaller(int depth) {
		try {
			Throwable t = new Throwable();
			StackTraceElement[] elements = t.getStackTrace();
			// String calleeMethod = elements[0].getMethodName();
			if (elements[depth] == null) {
				return "";
			}

			String callerMethodName = elements[depth].getMethodName();
			String callerClassName = elements[depth].getClassName();
			int callerLineNumber = elements[depth].getLineNumber();

			return callerClassName + "." + callerMethodName + "[" + callerLineNumber
					+ "]: ";
		} catch (Throwable t) {
			return "";
		}
	}

	/**
	 * Sets the log file name (if FILE logging is available)
	 * 
	 * @param logFileName
	 *            the name of the log file
	 */
	public void setLogFileImpl(String logFileName) {
		if (logFile != null && logFileWriter != null) {
			try {
				logFileWriter.close();
			} catch (IOException e) {
				log(Level.WARN, "Previous log file cannot be closed", 1);
			}
		}
		logFile = new File(logFileName);
		try {
			logFileWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(logFile), "UTF-8"));
		} catch (IOException e) {
			log(Level.WARN, "Log file " + logFileName + "cannot be opened", 1);
		}
	}

	private void print(String logEntry, Level level) {
		if (getLogDestination() == LogDestination.FILE) {
			if (logFileWriter != null) {
				try {
					logFileWriter.append(logEntry + "\n");
					logFileWriter.flush();
					return;
				} catch (IOException e) {
					// Falling back to use CONSOLE instead:
					setLogDestination(LogDestination.CONSOLE);
					log(Level.WARN, "Error writing log file", 1);
					// Trying again (recursive call):
					print(logEntry, level);
					return;
				}
			}
			setLogDestination(LogDestination.CONSOLE);
		}
		if (getLogDestination() == LogDestination.CONSOLE) {
			if (level.ordinal() <= Level.ERROR.ordinal()) {
				System.err.println(logEntry);
			} else {
				System.out.println(logEntry);
			}
		}
	}

	@Override
	public void doPrintStacktrace(String message) {
		try {
			// message null check done in caller
			throw new Exception(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
