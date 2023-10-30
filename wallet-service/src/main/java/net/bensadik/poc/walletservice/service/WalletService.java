package net.bensadik.poc.walletservice.service;


import jakarta.ws.rs.NotFoundException;

public interface WalletService {
    WalletResponse create(WalletCreateRequest request) throws AlreadyExistsException;

    WalletResponse update(String id, WalletCreateRequest request) throws NotFoundException, AlreadyExistsException;

    WalletResponse findById(String id) throws NotFoundException;

    PagingResponse<WalletResponse> findAll(int page, int size);


    boolean existsById(String id);

    void deleteById(String id) throws NotFoundException;
}
