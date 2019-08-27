var wsUri = "ws://localhost:8080/t-medicine-board-1.0-SNAPSHOT/board";

window.addEventListener("load", init, false);
function init() {
    output = document.getElementById("output");
}

websocket = new WebSocket(wsUri);

websocket.onopen = function(evt) {
    onOpen(evt)
};
websocket.onmessage = function(evt) {
    onMessage(evt)
};
websocket.onerror = function(evt) {
    onError(evt)
};

function send_message() {
    doSend("hello from JSF webSocket");
}
function onOpen(evt) {
    writeToScreen("Connected to Endpoint!");
    doSend(textID.value);
}
function onMessage(evt) {
    writeToScreen("Message Received: " + evt.data);
}
function doSend(message) {
    writeToScreen("Message Sent: " + message);
    websocket.send(message);
}
function onError(message) {
    console.log("error: " + message);
}
function writeToScreen(message) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;

    output.appendChild(pre);
}
