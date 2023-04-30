package com.waqas.akram.proposalgeniusapi.constant;

public interface AppConstants {
    /**
     * A constant string representing a message indicating no response was received.
     */
    String NO_RESPONSE_MESSAGE = "No valid response.";

    /**
     * The constant string used for appending a quote to the beginning and end of a string.
     */
    String APPEND_QUOTE = "\"";

    /**
     * The constant string used for indicating the job description prompt in the OpenAI chat request.
     */
    String JOB_DESCRIPTION_PROMPT = " and the job description is : ";

    /**
     * The constant string used for providing special instructions to the ChatGPT API for generating the prompt.
     */
    String SPECIAL_INSTRUCTION = " Special Instruction is to just give the response. Don't add the response like here's a sample job proposal on etc.";

    /**
     * This constant defines the API endpoint for generating proposals.
     */
    String GENERATE_PROPOSAL_API_ENDPOINT = "/generate-proposal";

    /**
     * The name of the index HTML file.
     */
    String INDEX_HTML_FILE_NAME = "index.html";
}