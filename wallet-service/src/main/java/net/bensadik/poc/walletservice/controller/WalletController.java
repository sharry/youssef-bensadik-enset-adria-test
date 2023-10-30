package net.bensadik.poc.walletservice.controller;

import net.bensadik.poc.walletservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
@Validated
public class WalletController {
    private final WalletService walletService;

    @GetMapping
    public ResponseEntity<PagingResponse<WalletResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                walletService.findAll(page, size)
        );
    }

    @GetMapping("/wallet/{id}")
    public ResponseEntity<WalletResponse> findById(
            @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(
                walletService.findById(id)
        );
    }

    @GetMapping("/wallet/{id}/exists")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(
                walletService.existsById(id)
        );
    }

    @DeleteMapping("/wallet/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("id") String id
    ) {
        walletService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<WalletResponse> create(
            @Valid @RequestBody WalletCreateRequest request
    ) throws AlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        walletService.create(request)
                );
    }

    @PatchMapping("/wallet/{id}")
    public ResponseEntity<WalletResponse> update(
            @PathVariable("id") String id,
            @Valid @RequestBody WalletCreateRequest request
    ) throws AlreadyExistsException {
        return ResponseEntity.ok(
                walletService.update(id, request)
        );
    }

}
