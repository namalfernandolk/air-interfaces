package sabre;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.ws.Holder;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.impl.DefaultCamelContext;

import org.ebxml.namespaces.messageheader.From;
import org.ebxml.namespaces.messageheader.MessageHeader;
import org.ebxml.namespaces.messageheader.PartyId;
import org.ebxml.namespaces.messageheader.Service;
import org.ebxml.namespaces.messageheader.To;
import org.opentravel.ota._2002._11.SessionCloseRQ;
import org.opentravel.ota._2002._11.SessionCloseRQ.POS;
import org.opentravel.ota._2002._11.SessionCloseRQ.POS.Source;
import org.opentravel.ota._2002._11.SessionCloseRS;
import org.xmlsoap.schemas.ws._2002._12.secext.Security;
import org.xmlsoap.schemas.ws._2002._12.secext.Security.UsernameToken;

import https.webservices_sabre_com.websvc.SessionClosePortType;
import https.webservices_sabre_com.websvc.SessionCreatePortType;

public class SessionClose {

	public ArrayList buildRequest() {

		Holder<MessageHeader> 	messageHeader 	= new Holder<MessageHeader>();

		MessageHeader 			messageHeader2 	= new MessageHeader();
		Service 				service 		= new Service();
		messageHeader2.setAction("SessionCreateRQ");

		From 					from 			= new From();
		PartyId 				partyIdFrom 	= new PartyId();
		partyIdFrom.setValue("999999");
		from.getPartyId().add(partyIdFrom);
		messageHeader2.setFrom(from );

		To 						to 				= new To();
		PartyId 				partyIdTo 		= new PartyId();
		partyIdTo.setValue("123123");
		to.getPartyId().add(partyIdTo);
		messageHeader2.setTo(to);

		messageHeader2.setConversationId("abc123");
		messageHeader2.setCPAId("IPCC");

		messageHeader.value	= messageHeader2;




		Holder<Security> 		securityHeader 	= new Holder<Security>();
		Security 				security 		= new Security();

		UsernameToken usernameToken = new UsernameToken();
		usernameToken.setUsername("digitalmicroservices");
		usernameToken.setPassword("%m!GnUv6vEKY");
		usernameToken.setOrganization("IPCC");
		usernameToken.setDomain("DEFAULT");

		security.setUsernameToken(usernameToken );

		securityHeader.value = security;


		SessionCloseRQ sessionCloseRQ = new SessionCloseRQ();
		POS 			pos 					= new POS();
		Source 			source 					= new Source();
		source.setPseudoCityCode("IPCC");
		pos.setSource(source );
		sessionCloseRQ.setPOS(pos);
		
		HashMap<String,Object> requestHeaders = new HashMap<String,Object>();
		requestHeaders.put("Username", usernameToken);

		ArrayList requestBody = new ArrayList();
		requestBody.add(messageHeader);
		requestBody.add(security);
		requestBody.add(sessionCloseRQ);

		return requestBody;

	}

	public SessionCloseRS invokeRequest(ArrayList requestBody) {

		CamelContext context = new DefaultCamelContext();

		CxfComponent cxfComponent = new CxfComponent(context);
		CxfEndpoint serviceEndpoint =
				new CxfEndpoint("https://webservices.sabre.com", cxfComponent);

		serviceEndpoint.setServiceClass(SessionClosePortType.class);

		ProducerTemplate template = context.createProducerTemplate();

		SessionCloseRS sessionCloseRS = template.requestBody(serviceEndpoint, requestBody, SessionCloseRS.class);
		
		return sessionCloseRS;


	}
	
	public String getSessionCloseStatus(SessionCloseRS sessionCloseRS) {
		
		System.out.println(sessionCloseRS.getConversationId());
		System.out.println(sessionCloseRS.getEchoToken());
		System.out.println(sessionCloseRS.getErrors());
		System.out.println(sessionCloseRS.getSequenceNmbr());
		System.out.println(sessionCloseRS.getStatus());
		System.out.println(sessionCloseRS.getSuccess());
		
		return sessionCloseRS.getStatus();
	}

	String url = null;

}
