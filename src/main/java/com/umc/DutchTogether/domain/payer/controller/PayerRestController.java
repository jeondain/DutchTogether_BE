package com.umc.DutchTogether.domain.payer.controller;

import com.umc.DutchTogether.domain.payer.dto.PayerRequest;
import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.payer.service.PayerCommandService;
import com.umc.DutchTogether.domain.payer.service.PayerQueryService;
import com.umc.DutchTogether.domain.payer.service.PayerQueryServiceImpl;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payers")
public class PayerRestController {

    private final PayerCommandService payerCommandService;
    private final PayerQueryService payerQueryService;

    @PostMapping("/")
    public ApiResponse<PayerResponse.PayerListDTO> createPayer(@Valid @RequestBody PayerRequest.PayerListDTO request) {
        PayerResponse.PayerListDTO payer = payerCommandService.createPayer(request);
        return ApiResponse.onSuccess(payer);
    }

    @GetMapping("/{meetingNum}")
    public ApiResponse<PayerResponse.PayerNameListDTO> getPayer(@PathVariable Long meetingNum){
        PayerResponse.PayerNameListDTO payerNameList = payerQueryService.getPayerList(meetingNum);
        return ApiResponse.onSuccess(payerNameList);
    }
}