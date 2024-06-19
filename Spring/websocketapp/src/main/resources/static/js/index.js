let socket = new WebSocket("http://localhost:8080/chat");
let currentChatId;

socket.onopen = function (event) {
    console.log("Connected to server");
    console.log("Getting chat list from server...")
    document.getElementById("messageBox").innerHTML = "";
    getChatList();
}

socket.onmessage = function (event) {
    let response = JSON.parse(event.data);
    switch (response.eventType) {
        case "CHAT_LIST_RESPONSE":
            handleChatListResponse(response)
            break;
        case "CHAT_CONNECTION_RESPONSE":
            handleConnectionResponse(response);
            break;
        case "MESSAGE_LIST_UPDATE_RESPONSE":
            handleMessageListUpdateResponse(response);
            break;
        case "ERROR_RESPONSE":
            alert("Error!");
            console.log("Got error response with message: " + response.payload);
            break;
        default:
            alert("Unknown event type: " + response.eventType);
            break;
    }
}
socket.onclose = function (event) {
    console.log("Connection closed");
}
socket.onerror = function (event) {
    console.log("Error: " + event.data);
    document.getElementById("messageBox").innerHTML = "Error: " + event.data;
}

function handleChatListResponse(response) {
    console.log(typeof response, typeof response.payload, response);
    let chatList = response.payload;

    var chatListDiv = document.createElement("div");
    chatListDiv.setAttribute("class", "chat-list");

    for (let chatListItem of chatList) {
        var chatListItemDiv = document.createElement("div");
        chatListItemDiv.setAttribute("class", "chat-list-item");
        chatListItemDiv.setAttribute("onclick", "openChat(" + chatListItem.chatId + ")");
        chatListItemDiv.innerHTML = chatListItem.chatName + " - " + chatListItem.chatDesc;
        chatListDiv.append(chatListItemDiv);
    }

    document.body.append(chatListDiv)
}

function handleConnectionResponse(response) {
    let messageArray = response.payload;

    let chatDiv = document.getElementById("chatBox");
    document.getElementsByClassName("chat-list")[0].remove();
    chatDiv.style.display = "flex";

    if (messageArray.length === 0) {
        document.getElementById("messageBox").innerHTML = "Ещё никто не написал в этот чат, будьте первыми!";
    }
    else {
        document.getElementById("messageBox").innerHTML = "";
        let messageListDiv = document.getElementById("message-list");
        for (let message of messageArray) {
            let messageDiv = document.createElement("div");
            messageDiv.setAttribute("class", "message");
            messageDiv.innerHTML = "Anonymus: " + message.messageText;
            messageListDiv.append(messageDiv);
        }
    }
}
function openChat(chatId) {
    let request = {
        "eventType": "CHAT_CONNECTION_REQUEST",
        "payload": {
            "chatId": chatId
        }
    }

    if (socket.readyState === WebSocket.OPEN) {
        socket.send(JSON.stringify(request));
        currentChatId = chatId;
    }
}

function handleMessageListUpdateResponse(response) {
    let messageArray = response.payload;
    let messageListDiv = document.getElementById("message-list");
    messageListDiv.innerHTML = "";

    for (let message of messageArray) {
        let messageDiv = document.createElement("div");
        messageDiv.setAttribute("class", "message");
        messageDiv.innerHTML = "Anonymus: " + message.messageText;
        messageListDiv.append(messageDiv);
    }
}

function getChatList() {
    let request = {
        "eventType": "CHAT_LIST_REQUEST",
        "payload": null
    }
    if (socket.readyState === WebSocket.OPEN) {
        socket.send(JSON.stringify(request));
    }
}

function messageSend() {
    let message = document.getElementById("messageArea").value;
    let request = {
        "eventType": "MESSAGE_POST",
        "payload": {
            "message": message
        }
    }
    if (socket.readyState === WebSocket.OPEN) {
        socket.send(JSON.stringify(request));
        document.getElementById("messageArea").value = "";
    }
}