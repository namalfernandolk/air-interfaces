package sabre;

public class SabreLowFareSearch {

	public static void main(String[] args) {
	
		SessionCreate  sessionCreate = new SessionCreate();
		
		String sessionId = sessionCreate.getSessionId(sessionCreate.invokeRequest(sessionCreate.buildRequest()));
		System.out.println(sessionId);
		
		// build the LowFareSearch with the sessionID
		// invoke the request
		// decode the response
		
		
		SessionClose sessionClose = new SessionClose();
		String status = sessionClose.getSessionCloseStatus(sessionClose.invokeRequest(sessionClose.buildRequest()));

	}
	
}
