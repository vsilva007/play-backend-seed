package utils;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class PdbExecutionContext extends CustomExecutionContext {
	@Inject
	public PdbExecutionContext(ActorSystem actorSystem) {
		// uses a custom thread pool defined in application.conf
		super(actorSystem, "pdb_dispatcher");
	}
}
