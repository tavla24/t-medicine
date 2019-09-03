// var wsUri = "ws://localhost:8080/t-medicine-board-1.0-SNAPSHOT/board";
var wsUri = "ws://localhost:8080/t-medicine-board/board";

window.addEventListener("load", init, false);
function init() {
    //dataTableWidgetVar.filter();
}

console.log("create websocket");
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
    //doSend(textID.value);
}
function onMessage(evt) {
    location.reload();
}
function doSend(message) {
    websocket.send(message);
}
function onError(message) {
    console.log("error: " + message);
}
