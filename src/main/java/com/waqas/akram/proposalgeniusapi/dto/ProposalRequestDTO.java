package com.waqas.akram.proposalgeniusapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents a data transfer object (DTO) for a proposal request.
 * It contains information about the user profile, job description, and proposal type.
 */
@Setter
@Getter
@ToString
public class ProposalRequestDTO {
    /**
     * The user profile for the proposal request.
     */
    private String userProfile;

    /**
     * The job description for the proposal request.
     */
    private String jobDescription;

    /**
     * The type of proposal request.
     */
    private ProposalType type;

    /**
     * An enum representing the different types of proposal requests.
     * The available types are job proposal and cover letter.
     */
    public enum ProposalType {
        JOB_PROPOSAL, COVER_LETTER
    }
}