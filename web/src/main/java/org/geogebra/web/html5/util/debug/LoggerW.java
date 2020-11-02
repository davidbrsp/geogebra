package org.geogebra.web.html5.util.debug;

import java.util.Date;

import org.geogebra.common.util.debug.Log;
import org.geogebra.web.html5.util.AppletParameters;

import com.google.gwt.user.client.Window;

import elemental2.dom.DomGlobal;
import jsinterop.base.Js;

/**
 * GeoGebraLogger implementation for the web platform
 * 
 * @author Zoltan Kovacs
 */
public class LoggerW extends Log {

	private String getTimeInfo() {
		Date date = new Date();
		return date.getHours() + ":" + date.getMinutes() + ":"
				+ date.getSeconds() + ": ";
	}

	@Override
	public void log(Level level, Object logEntry, int depth) {
		switch (level) {
		case INFO:
			DomGlobal.console.info(getTimeInfo(), logEntry);
			break;
		case WARN:
			DomGlobal.console.warn(getTimeInfo(), logEntry);
			break;
		case ERROR:
			DomGlobal.console.error(getTimeInfo(), logEntry);
			break;
		case TRACE:
			DomGlobal.console.trace(getTimeInfo(), logEntry);
			break;
		default:
		case DEBUG:
			DomGlobal.console.log(getTimeInfo(), logEntry);
			break;
		}
	}

	/**
	 * Start logger if parameters allow it.
	 * 
	 * @param article
	 *            parameters
	 */
	public static void startLogger(AppletParameters article) {
		if (article.getDataParamShowLogging()) {
			Log.setLogger(new LoggerW());
			Log.setLogDestination(LogDestination.CONSOLE);
			Log.setLogLevel(Window.Location.getParameter("logLevel"));
		}
	}

	@Override
	public void doPrintStacktrace(String message) {
		DomGlobal.console.trace(message);
	}

	@Override
	protected void doPrintStacktrace(Throwable t) {
		// This contains the stacktrace in gwt dev mode.
		Object backingJsObject = Js.asPropertyMap(t).get("backingJsObject_1_g$");
		DomGlobal.console.log(backingJsObject != null ? backingJsObject : t);
	}

	public static void loaded(String string) {
		debug("Loaded: " + string);
	}
}
