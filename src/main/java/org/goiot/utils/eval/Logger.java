package org.goiot.utils.eval;


public class Logger {

	public static final Logger instance = new Logger();
	private ILogger logger;
	private boolean isLog;

	private Logger() {
		logger = new ILogger() {

			@Override
			public String append(String log) {
				System.out.print(log);
				return log;
			}
		};
		isLog = true;
	}

	public ILogger getLogger() {
		return logger;
	}

	public void setLogger(ILogger logger) {
		this.logger = logger;
	}

	public boolean isLog() {
		return isLog;
	}

	public void setLog(boolean isLog) {
		this.isLog = isLog;
	}

	public String append(String log) {
		if (isLog) {
			logger.append(log);
		}
		return log;
	}

	public String appendLine(String log) {
		log += "\n";
		if (isLog) {
			logger.append(log);
		}
		return log;
	}

	public String appendFormat(String format, Object... params) {
		String log = String.format(format, params);
		if (isLog) {
			logger.append(log);
		}
		return log;
	}

}
