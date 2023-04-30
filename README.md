# Proposal Genius

Proposal Genius is a Spring Boot web application that provides an API endpoint for generating job proposals and cover letters for freelancing platforms like Upwork.

## Usage

To use Proposal Genius, simply run the Spring Boot application and send a request to the `/generate-proposal` endpoint with the required parameters.


## Configuration

Proposal Genius uses OpenAI's GPT-3 language model to generate proposals and cover letters. To use the application, you will need to provide your OpenAI API key and specify which GPT-3 model to use.

### OpenAI API Key

To generate an API key for your OpenAI account, follow these steps:

1. Log in to your OpenAI account and navigate to the "View API Keys" page.
2. Click on the "Create New Secret Key" button and follow the prompts to create a new API key.
3. Copy the API key to your clipboard.

### API Key Property

To set the `openai.api.key` property, add it to the `application.properties` file. For example, set the property in the `src/main/resources/application.properties` file, add the following line:
````
openai.api.key=<YOUR_API_KEY_HERE>
````

### GPT-3 Model

Proposal Genius supports multiple GPT-3 models, each with different capabilities and performance characteristics. To specify which model to use, set the `openai.model` property in the `application.properties` file. For example, to use the "GPT-3.5 Turbo" model, add the following line to your `application.properties` file:

````
openai.model=gpt-3.5-turbo
````

### API Endpoint

The `/generate-proposal` endpoint supports the following HTTP method:

- `POST`: Generates a new proposal or cover letter based on the JSON payload sent in the request body based on the type.

### Request Parameters

The following parameters are required to generate a proposal or cover letter:

- `userProfile`: The user's profile information, such as name and contact details.
- `jobDescription`: The job description for the proposal or cover letter.
- `type`: The type of document to generate (`JOB_PROPOSAL` or `COVER_LETTER`).

### Example Request

````
{
    "userProfile": "I have 4 years of experience in springboot, microserviee dev and mentoring others",
    "jobDescription": "Need a java spring boot tutor with online teaching",
    "type": "JOB_PROPOSAL"
}
````

### Example Response

````
{
    "message": "<Generated Proposal Here>"
}    
````

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
