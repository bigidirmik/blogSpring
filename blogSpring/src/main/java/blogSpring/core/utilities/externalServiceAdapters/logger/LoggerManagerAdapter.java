package blogSpring.core.utilities.externalServiceAdapters.logger;

import blogSpring.externalServices.jLogger.JLoggerManager;

public class LoggerManagerAdapter implements LoggerService {

	@Override
	public void logToSystem(String message) {
		JLoggerManager jLoggerManager = new JLoggerManager();
		jLoggerManager.log(message);
	}

}
