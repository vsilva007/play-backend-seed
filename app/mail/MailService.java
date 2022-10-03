package mail;

import com.typesafe.config.Config;
import jodd.mail.*;
import play.Logger;
import utils.EncryptionUtils;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

public class MailService {
	private final Config config;

	@Inject()
	public MailService(Config config) {
		this.config = config;
	}

	public Email formatMail(String destination, String subject, InputStream is) throws IOException {
		return Email.create().from(this.config.getString("mail.configuration.smtp_user")).to(destination).bcc(this.config.getString("mail.configuration.cco_recipients").split(",")).subject(subject)
				.htmlMessage("<html><META http-equiv=Content-Type content=\"text/html; " + "charset=utf-8\"><body><img src='cid:image.png'>" + "</body></html>").embeddedAttachment(EmailAttachment.with().content(is, "image/png"));
	}

	public Email formatMail(String destination, String subject, String msg) {
		return Email.create().from(this.config.getString("mail.configuration.smtp_user")).to(destination.split(",")).bcc(this.config.getString("mail.configuration.cco_recipients").split(",")).subject(subject).htmlMessage(msg);
	}

	public boolean sendMail(Email email) {
		SendMailSession session = null;
		try {
			session = this.buildAndTestSession();
			session.sendMail(email);
			System.out.println("Email sended: " + email.toString());
			return true;
		} catch (Exception ex) {
			Logger.error(ex.getMessage(), ex);
			return false;
		} finally {
			if (session != null)
				try {
					session.close();
				} catch (Exception ex) {
				}
		}
	}

	public SendMailSession buildAndTestSession() throws Exception {
		SendMailSession session = null;
		boolean mailEnabled = this.config.getBoolean("mail.configuration.smtp_enabled");
		if (!mailEnabled)
			return null; // discard buildAndTestSession if disabled by property
		int port = this.config.getInt("mail.configuration.smtp_port");
		boolean ssl = this.config.getBoolean("mail.configuration.smtp_use_ssl");
		boolean tls = this.config.getBoolean("mail.configuration.smtp_start_tls");
		session = getMailSession(this.config.getString("mail.configuration.smtp_server"), port, ssl, tls);
		session.open();
		return session;
	}

	private SendMailSession getMailSession(String host, int port, boolean ssl, boolean tls) throws Exception {
		if (ssl)
			return this.getSslMailSession(host, port, tls);
		if (!ssl && tls)
			return this.getTlsNoSslMailSession(host, port);
		// no ssl && no tls
		return this.getPlainMailSession(host, port);
	}

	private SendMailSession getPlainMailSession(String host, int port) throws Exception {
		SendMailSession session = null;
		SmtpServer smtpServer = new SmtpServer(host, port, new SimpleAuthenticator(this.config.getString("mail.configuration.smtp_user"), EncryptionUtils.decryptDESede(this.config.getString("mail.configuration.smtp_pwd"))));
		session = smtpServer.createSession();
		return session;
	}

	private SendMailSession getTlsNoSslMailSession(String host, int port) throws Exception {
		SendMailSession session = null;
		SmtpTlsNoSslServer smtpServer = new SmtpTlsNoSslServer(host, port, new SimpleAuthenticator(this.config.getString("mail.configuration.smtp_user"), EncryptionUtils.decryptDESede(this.config.getString("mail.configuration.smtp_pwd"))));
		session = smtpServer.createSession();
		return session;
	}

	private SendMailSession getSslMailSession(String host, int port, boolean tls) throws Exception {
		SendMailSession session = null;
		SmtpSslServer smtpServer = new SmtpSslServer(host, port, new SimpleAuthenticator(this.config.getString("mail.configuration.smtp_user"), EncryptionUtils.decryptDESede(this.config.getString("mail.configuration.smtp_pwd"))));
		if (tls)
			smtpServer.startTlsRequired(tls);
		session = smtpServer.createSession();
		return session;
	}
}
