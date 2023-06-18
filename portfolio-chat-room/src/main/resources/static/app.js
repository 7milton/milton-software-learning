var stompClient = null;

function setConnectedButtons(connected) {
    $("#connect").prop("disabled", connected);
    $("#send").prop("disabled", !connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/chat-room-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnectedButtons(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/general/messages', function (message) {
            showNewMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnectedButtons(false);
    console.log("Disconnected");
}

function sendMessage() {
    let message = {
        'author': {
            'name': $("#name").val()
        },
        'content': $("#message").val()
    };
    stompClient.send("/app/chat", {}, JSON.stringify(message));
    $("#message").val('');
}

function showNewMessage(message) {
    $("#messages").append("<tr><td>" + message.author.name + "</td><td>" + message.content + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendMessage();
    });
});