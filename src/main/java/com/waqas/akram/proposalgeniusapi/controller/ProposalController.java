package com.waqas.akram.proposalgeniusapi.controller;

import com.waqas.akram.proposalgeniusapi.dto.ProposalRequestDTO;
import com.waqas.akram.proposalgeniusapi.service.ProposalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.waqas.akram.proposalgeniusapi.constant.AppConstants.GENERATE_PROPOSAL_API_ENDPOINT;

/**
 * A REST controller class that handles chat requests.
 * <p>
 * This controller creates a chat request and sends it to the OpenAI API, then returns the first message from the API response to the client.
 * The chat request is created with a prompt specified as a request parameter.
 */
@RestController
@Slf4j
public class ProposalController {
    /**
     * The ProposalService instance used to create and send chat requests.
     */
    @Autowired
    private ProposalService service;

    /**
     * Handles GET requests to the /generate-proposal by creating a chat request with the
     * specified prompt and sending it to the OpenAI API. Returns the first message from
     * the API response.
     *
     * @param proposalRequest the prompt to send to the API
     * @return a ResponseEntity object containing the first message from the API response
     */
    @PostMapping(value = GENERATE_PROPOSAL_API_ENDPOINT)
    public ResponseEntity<Object> generateProposal(@RequestBody ProposalRequestDTO proposalRequest) {
        log.info("Received POST request on {} endpoint with proposalRequest: {}", GENERATE_PROPOSAL_API_ENDPOINT, proposalRequest);
        return service.generateProposal(proposalRequest);
    }
}