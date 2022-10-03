package utils;

import play.Environment;
import play.inject.ApplicationLifecycle;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;

// This creates an `ApplicationStart` object once at start-up.
@Singleton
public class ApplicationStart {
	// Inject the application's Environment upon start-up and register hook(s) for shut-down.
	@Inject
	public ApplicationStart(ApplicationLifecycle lifecycle, Environment environment) {
		lifecycle.addStopHook(() -> {
			return CompletableFuture.completedFuture(null);
		});
	}
}
