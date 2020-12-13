<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>BankTeller</title>
    <meta name="author" content="lafranch">
    <meta name="description" content="Lex Runtime example from the browser.">
    <meta name="keywords" content="Amazon Lex, SDK, Runtime, Browser, JavaScript">
    <link rel="icon" type="image/png" href="image/favicon-32x32.png" sizes="32x32"/>
    <link rel="icon" type="image/png" href="image/favicon-16x16.png" sizes="16x16"/>
    <link rel="stylesheet" href="css/stylebot.css">
</head>

<body class="wrapper">
<h1 style="text-align: center;">Hello <%=request.getAttribute("userName")%></h1>
<p style="text-align: center;"><a href="index.html" style="text-align: center;">Logout</a></p>
<div class="audio-control">
    <p id="audio-control" class="white-circle">
        <img src="image/lex.png">
        <canvas class="visualizer"></canvas>
    </p>
    <p><span id="message"></span></p>
    <p>
        <input type="hidden" id="ACCESS_ID" name="ACCESS ID" placeholder="ACCESS ID" value=""/>
    </p>
    <p>
        <input type="hidden" id="SECRET_KEY" name="SECRET KEY" placeholder="SECRET KEY" value=""/>
    </p>
    <p>
        <input type="hidden" id="BOT" name="BOT" placeholder="BOT" value="BankTeller"/>
    </p>
</div>
<script src="https://sdk.amazonaws.com/js/aws-sdk-2.48.0.min.js"></script>
<script src="dist/aws-lex-audio.js" type="text/javascript"></script>
<script src="js/renderer.js" type="text/javascript"></script>
<script type="text/javascript">
    var waveform = window.Waveform();
    var message = document.getElementById('message');
    var config, conversation;
    message.textContent = 'Passive';

    document.getElementById('audio-control').onclick = function () {

        AWS.config.credentials = new AWS.Credentials(document.getElementById('ACCESS_ID').value, document.getElementById('SECRET_KEY').value, null);
        AWS.config.region = 'us-east-1';
        
        config = {
            lexConfig: { botName: document.getElementById('BOT').value }
        };

        conversation = new LexAudio.conversation(config, function (state) {
            message.textContent = state + '...';
            if (state === 'Listening') {
                waveform.prepCanvas();
            }
            if (state === 'Sending') {
                waveform.clearCanvas();
            }
        }, function (data) {
            console.log('Transcript: ', data.inputTranscript, ", Response: ", data.message);
        }, function (error) {
            message.textContent = error;
        }, function (timeDomain, bufferLength) {
            waveform.visualizeAudioBuffer(timeDomain, bufferLength);
        });
        conversation.advanceConversation();
    };

</script>
</body>

</html>