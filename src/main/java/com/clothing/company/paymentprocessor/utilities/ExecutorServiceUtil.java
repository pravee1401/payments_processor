package com.clothing.company.paymentprocessor.utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ExecutorServiceUtil {

	@Bean("fixedThreadPool")
	public ExecutorService fixedThreadPool() {
		int cores = Runtime.getRuntime().availableProcessors();
		return Executors.newFixedThreadPool(cores);
	}
}
