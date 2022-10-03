package tasks;

import akka.actor.ActorSystem;
import com.github.tuxBurner.jobs.AbstractAnnotatedJob;
import com.github.tuxBurner.jobs.AkkaJob;
import com.github.tuxBurner.jobs.JobException;
import services.LockService;

/**
 * Simple job which is fired every 15 minutes
 */
@AkkaJob(cronExpression = "0 0/15 * * * ?")
public class LockTask extends AbstractAnnotatedJob {
	private LockService lockService;

	public LockTask(ActorSystem actorSystem) throws JobException {
		super(actorSystem);
		this.lockService = new LockService();
	}

	@Override
	public void runInternal() {
		this.lockService.deleteTenMinutesOlder();
	}
}

