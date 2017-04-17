var http = require('http');
const querystring = require('querystring');

exports.handler = (event, context) => {

  try {

    if (event.session.new) {
      // New Session
      console.log("NEW SESSION")
    }

    switch (event.request.type) {

      case "LaunchRequest":
        // Launch Request
        console.log(`LAUNCH REQUEST`)
        context.succeed(
          generateResponse(
            buildSpeechletResponse("Welcome to an Alexa Skill, this is running on a deployed lambda function", true),
            {}
          )
        )
        break;

      case "IntentRequest":
        // Intent Request
        console.log(`INTENT REQUEST`)

        switch(event.request.intent.name) {
        case "GetIntroduction":
            console.log(`Hit the GetIntroduction request:`);
            var endpoint = "http://83.162.137.7:8080/alexa/intro" // ENDPOINT GOES HERE
            var body = ""
            http.get(endpoint, (response) => {
              response.on('data', (chunk) => { body += chunk })
              response.on('end', () => {
                var data = body;
                console.log(`GetIntroduction data: ${data}`);
                context.succeed(
                  generateResponse(
                    buildSpeechletResponse(`${data}`, true),
                    {}
                  )
                )
              }).on('error', (e) => {
                console.error(`Got error: ${e.message}`);
              })
            })
            break;

          case "GetCocktails":
            console.log(`Hit the GetCocktails request:`);
            var endpoint = "http://83.162.137.7:8080/alexa/cocktails" // ENDPOINT GOES HERE
            var body = ""
            http.get(endpoint, (response) => {
              response.on('data', (chunk) => { body += chunk })
              response.on('end', () => {
                var data = body;
                console.log(`GetCocktails data: ${data}`);
                context.succeed(
                  generateResponse(
                    buildSpeechletResponse(`${data}`, true),
                    {}
                  )
                )
              }).on('error', (e) => {
                console.error(`Got error: ${e.message}`);
              })
            })
            break;

          case "GetCocktailsDetailed":
            console.log(`Hit the GetCocktailsDetailed request: ${JSON.stringify(event.request.intent.slots)}`);
            var cocktailName = event.request.intent.slots.Cocktail.value;
            console.log(event.request.intent.slots.Cocktail.value);
            var params = querystring.stringify({ 'cocktailName': cocktailName });
            var endpoint = "http://83.162.137.7:8080/alexa/cocktails/detailed?" + params; // ENDPOINT GOES HERE
            var body = ""
            http.get(endpoint, (response) => {
              response.on('data', (chunk) => { body += chunk })
              response.on('end', () => {
                var data = body;
                console.log(`GetCocktailsDetailed data: ${data}`);
                context.succeed(
                  generateResponse(
                    buildSpeechletResponse(`${data}`, true),
                    {}
                  )
                )
              }).on('error', (e) => {
                console.error(`Got error: ${e.message}`);
              })
            })
            break;

          case "GetSubscriberCount":
            var endpoint = "" // ENDPOINT GOES HERE
            var body = ""
            http.get(endpoint, (response) => {
              response.on('data', (chunk) => { body += chunk })
              response.on('end', () => {
                var data = JSON.parse(body)
                var subscriberCount = data.items[0].statistics.subscriberCount
                context.succeed(
                  generateResponse(
                    buildSpeechletResponse(`Current subscriber count is ${subscriberCount}`, true),
                    {}
                  )
                )
              })
            })
            break;

          case "GetVideoViewCount":
            var endpoint = "" // ENDPOINT GOES HERE
            var body = ""
            http.get(endpoint, (response) => {
              response.on('data', (chunk) => { body += chunk })
              response.on('end', () => {
                var data = JSON.parse(body)
                var viewCount = data.items[0].statistics.viewCount
                context.succeed(
                  generateResponse(
                    buildSpeechletResponse(`Current view count is ${viewCount}`, true),
                    {}
                  )
                )
              })
            })
            break;

          case "GetVideoViewCountSinceDate":
            console.log(event.request.intent.slots.SinceDate.value)
            var endpoint = "" // ENDPOINT GOES HERE
            var body = ""
            http.get(endpoint, (response) => {
              response.on('data', (chunk) => { body += chunk })
              response.on('end', () => {
                var data = JSON.parse(body)
                var viewCount = data.items[0].statistics.viewCount
                context.succeed(
                  generateResponse(
                    buildSpeechletResponse(`Current view count is ${viewCount}`, true),
                    {}
                  )
                )
              })
            })
            break;

          default:
            throw "Invalid intent"
        }

        break;

      case "SessionEndedRequest":
        // Session Ended Request
        console.log(`SESSION ENDED REQUEST`)
        break;

      default:
        context.fail(`INVALID REQUEST TYPE: ${event.request.type}`)

    }

  } catch(error) { context.fail(`Exception: ${error}`) }

}

// Helpers
buildSpeechletResponse = (outputText, shouldEndSession) => {

  return {
    outputSpeech: {
      type: "PlainText",
      text: outputText
    },
    shouldEndSession: shouldEndSession
  }

}

generateResponse = (speechletResponse, sessionAttributes) => {

  return {
    version: "1.0",
    sessionAttributes: sessionAttributes,
    response: speechletResponse
  }

}