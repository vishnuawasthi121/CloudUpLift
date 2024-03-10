package com.poc.cacheclient.app.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.cacheclient.app.dto.EmployeeDTO;

@RestController
public class OrderController {

	// @Autowired
	// IgniteClient igniteClient;

	@GetMapping(path = "/readData/{cacheName}")
	public Object getOrders(@PathVariable String cacheName, @RequestParam(name = "keys") String[] keys) {
		ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
		try (IgniteClient client = Ignition.startClient(cfg)) {
			ClientCache<Object, Object> cache = client.cache("RN_BFL");
			// Get data from the cache
			System.out.println(cache);
			Set<Integer> asList = Arrays.asList(keys).stream().map(item -> Integer.valueOf(item))
					.collect(Collectors.toSet());
			System.out.println("KEYS   : " + asList);
			Map<Object, Object> all = cache.getAll(new HashSet<>(asList));
			System.out.println(all);
			return all;
		}

	}

	@GetMapping(path = "/orders/add")
	public Object getAddOrders() {
		ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
		try (IgniteClient client = Ignition.startClient(cfg)) {
			ClientCache<Integer, String> cache = client.cache("PRICE_BFL");
			// Get data from the cache
			System.out.println(cache);
			cache.put(1, "My First Entry");
			cache.put(2, "My 2 Entry");
			cache.put(3, "My 3 Entry");
			cache.put(4, "My 4 Entry");
			cache.put(5, "My 5 Entry");
			cache.put(6, "My 6 Entry");
		}
		return "Added data to cache";
	}

	@GetMapping(path = "/orders/readNonpersistent")
	public Object readNonpersistent() {
		ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
		try (IgniteClient client = Ignition.startClient(cfg)) {
			ClientCache<Integer, String> cache = client.cache("PRICE_BFL");
			Set<Integer> asList = new HashSet<>();
			asList.add(1);
			asList.add(2);
			asList.add(3);
			asList.add(4);
			asList.add(5);
			asList.add(6);
			asList.add(7);
			asList.add(8);
			Map<Integer, String> allData = cache.getAll(asList);
			return allData;
		}
	}

	@GetMapping(path = "/persitent/add/{startIndex}")
	public Object addToPersistentCache(@PathVariable Integer startIndex) {
		ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
		int counter = 0;
		try (IgniteClient client = Ignition.startClient(cfg)) {
			ClientCache<Object, Object> cache = client.cache("RN_BFL");
			// Get data from the cache
			System.out.println(cache);
			int totalToAdd = startIndex + 10;
			for (int i = startIndex; i <= totalToAdd; i++) {
				System.out.println("Key : " + i);
				EmployeeDTO emp = new EmployeeDTO(i, "This is the message for employee id - " + i);
				cache.put(i, emp);
				counter++;
			}
		}
		System.out.println("counter   : " + (counter-1));
		return String.format("Added data to persistent cache. Total item has been added {}", counter);
	}

	@GetMapping(path = "/cachenames")
	public Object getCacheNames() {
		ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
		try (IgniteClient client = Ignition.startClient(cfg)) {
			Collection<String> cacheNames = client.cacheNames();
			// Get data from the cache
			cacheNames.forEach(cache -> {
				System.out.println(cache);
			});
		}
		return "Printed cache names in console";
	}

}
