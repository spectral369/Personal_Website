package com.freelancingpter.workers;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.EnableAsync;

import com.freelancingpeter.data.AccountsInfo;
import com.freelancingpeter.data.Data;

@EnableAsync
public class ScheduledTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9103564419135430747L;

	ScheduledExecutorService executor = null;
	private static ScheduledTask updater = null;

	public ScheduledTask() {
		executor = Executors.newScheduledThreadPool(2, new ThreadFactory() {
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setDaemon(true);
				return thread;
			}
		});

	}

	public static ScheduledTask getInstance() {
		if (updater == null) {
			updater = new ScheduledTask();

		}
		return updater;
	}

	public void startExecutionAt() {
		Runnable taskWrapper = new Runnable() {

			@Override
			public void run() {
				Data.INSTANCE.clearLists();

				SteamLoadingThread.getInstance(AccountsInfo.getInstance()).run();
				MastodonLoadingThread.getInstance(AccountsInfo.getInstance()).run();

			}

		};
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));

		executor.scheduleWithFixedDelay(taskWrapper, 0L, 12L, TimeUnit.HOURS);
	}

}