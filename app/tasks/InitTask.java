package tasks;

import com.google.inject.AbstractModule;
import play.api.db.evolutions.DynamicEvolutions;
import play.db.ebean.DefaultEbeanConfig;
import play.db.ebean.EbeanConfig;
import play.db.ebean.EbeanDynamicEvolutions;
import security.JwtValidator;
import security.JwtValidatorImpl;
import utils.ApplicationStart;

public class InitTask extends AbstractModule {
	@Override
	protected void configure() {
		bind(ApplicationStart.class).asEagerSingleton();
		// JWT
		bind(JwtValidator.class).to(JwtValidatorImpl.class).asEagerSingleton();
		bind(DynamicEvolutions.class).to(EbeanDynamicEvolutions.class);
		bind(EbeanConfig.class).toProvider(DefaultEbeanConfig.EbeanConfigParser.class);
	}
}