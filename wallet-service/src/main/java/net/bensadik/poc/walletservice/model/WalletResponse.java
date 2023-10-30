package net.bensadik.poc.walletservice.model;

import java.time.LocalDate;

public class WalletResponse {
	private String id;
	private double balance;
	private String currency;
	private LocalDate createdDate;
	private Integer customerId;
}
