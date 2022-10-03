package mail;

import jodd.mail.SmtpServer;

import javax.mail.Authenticator;
import java.util.Properties;

/**
 * Created by victordasilva on 15/2/18.
 */
public class SmtpTlsNoSslServer extends SmtpServer {
	public SmtpTlsNoSslServer(String host, int port, Authenticator authenticator) {
		super(host, port, authenticator);
	}

	@Override
	protected Properties createSessionProperties() {
		Properties properties = super.createSessionProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		return properties;
	}
}
