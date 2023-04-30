package com.waqas.akram.proposalgeniusapi.service;


import com.waqas.akram.proposalgeniusapi.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.waqas.akram.proposalgeniusapi.constant.AppConstants.*;

/**
 * A service class that handles chat requests and responses by interacting with the OpenAI API.
 */
@Service
@Slf4j
public class ProposalService {
    /**
     * The RestTemplate instance used to make HTTP requests to the OpenAI API.
     */
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    /**
     * The name of the OpenAI model used for chat requests.
     */
    @Value("${openai.model}")
    private String model;

    /**
     * The URL of the OpenAI API endpoint for chat requests.
     */
    @Value("${openai.api.url}")
    private String apiUrl;

    /**
     * Generates a job proposal or cover letter based on the given {@link ProposalRequestDTO} object
     * and returns a {@link ResponseEntity} containing a {@link ResponseMessage} object with the generated proposal
     * or an error message if the request is invalid or if there was an error retrieving a response from the OpenAI API.
     *
     * @param proposalRequest The {@link ProposalRequestDTO} object containing the user profile, job description, and proposal type.
     * @return A {@link ResponseEntity} object containing a {@link ResponseMessage} object with the generated proposal or an error message.
     */
    public ResponseEntity<Object> generateProposal(ProposalRequestDTO proposalRequest) {
        ResponseEntity<Object> responseEntity = validateProposalRequestDTO(proposalRequest);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return responseEntity;
        }

        OpenAiChatRequest request = getOpenAiChatRequest(proposalRequest);

        log.info("Payload POST to OpenAI API: {}", request);
        try {
            OpenAiChatResponse response = restTemplate.postForObject(apiUrl, request, OpenAiChatResponse.class);

            if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            log.debug("Complete OpenAiChatResponse from OpenAI API : {}", response);
            String responseMessageContentFromChatGPT = response.getChoices()
                    .stream()
                    .findFirst()
                    .map(OpenAiChatResponse.Choice::getMessage)
                    .map(Message::getContent)
                    .orElse(NO_RESPONSE_MESSAGE);

            return ResponseEntity.ok()
                    .body(new ResponseMessage(responseMessageContentFromChatGPT));
        } catch (RestClientException e) {
            log.error("Failed to retrieve response from OpenAI API: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("Failed to retrieve response from OpenAI API: " + e.getMessage()));
        }
    }

    /**
     * Generates an OpenAI chat request object based on the given proposal request.
     *
     * @param proposalRequest The proposal request object containing user profile, job description and type of proposal.
     * @return An OpenAiChatRequest object with a prompt for generating job proposal or cover letter based on the proposal request.
     */
    private OpenAiChatRequest getOpenAiChatRequest(ProposalRequestDTO proposalRequest) {
        StringBuilder promptBuilder = new StringBuilder();

        if (proposalRequest.getType().equals(ProposalRequestDTO.ProposalType.JOB_PROPOSAL)) {
            promptBuilder.append("Hi, can you help me write a job proposal on Upwork? I need to include some emojis and proper bullet points to highlight the applicant's profile");
        } else if (proposalRequest.getType().equals(ProposalRequestDTO.ProposalType.COVER_LETTER)) {
            promptBuilder.append("Can you help me create an 1 page attention-grabbing cover letter for job. I want to impress the potential employer with my skills and experience in the field as mentioned : ");
        }

        promptBuilder.append(APPEND_QUOTE)
                .append(proposalRequest.getUserProfile())
                .append(APPEND_QUOTE)
                .append(JOB_DESCRIPTION_PROMPT)
                .append(APPEND_QUOTE)
                .append(proposalRequest.getJobDescription())
                .append(APPEND_QUOTE)
                .append(SPECIAL_INSTRUCTION);

        return new OpenAiChatRequest(model, promptBuilder.toString());
    }

    /**
     * Validates the ProposalRequestDTO object.
     *
     * @param proposalRequest the ProposalRequestDTO object to validate
     * @return a ResponseEntity with a ResponseMessage indicating any validation errors, or a 200 OK status if no errors are found
     */
    private ResponseEntity<Object> validateProposalRequestDTO(ProposalRequestDTO proposalRequest) {
        if (proposalRequest == null) {
            return new ResponseEntity<>(new ResponseMessage("Invalid request. Request body is null."), HttpStatus.BAD_REQUEST);
        }

        if (proposalRequest.getUserProfile().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("User Profile is mandatory field. Please add profile."), HttpStatus.BAD_REQUEST);
        }

        if (proposalRequest.getJobDescription().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Job Description is mandatory field. Please add Job Description."), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
    }
}