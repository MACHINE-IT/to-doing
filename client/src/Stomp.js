// const Stomp = require('stompjs');
// const SockJS = require('sockjs-client');

// const serverUrl = 'http://localhost:8082/ws'; // Adjust the WebSocket URL accordingly
// const username = 'TestUser'; // Set your username

// const socket = new SockJS(serverUrl);

// // Create a Stomp client over the SockJS connection
// const stompClient = Stomp.over(socket);

// stompClient.connect({}, (frame) => {
//     console.log('Connected to STOMP over SockJS');

//     stompClient.send('/app/chat.addUser', {}, JSON.stringify({
//         sender: username,
//         type: 'JOIN'
//     }));

//     // Send a STOMP message
//     stompClient.send('/app/chat.sendMessage', {}, JSON.stringify({
//         sender: username,
//         content: 'Hello, STOMP over HTTP!',
//         type: 'CHAT'
//     }));

//     // Subscribe to a STOMP destination
//     stompClient.subscribe('/topic/public', (message) => {
//         const receivedMessage = JSON.parse(message.body);
//         console.log('Received message:', receivedMessage);

//         // Add your assertions here to validate the received message.
//     });
// });
